package com.xwinter.study.activiti.web.system;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xwinter.study.access.Page;
import com.xwinter.study.access.PermissionManager;
import com.xwinter.study.activiti.common.Utils;
import com.xwinter.study.activiti.entity.system.Permission;
import com.xwinter.study.activiti.service.system.PermissionService;
import com.xwinter.study.activiti.web.BaseController;
import com.xwinter.study.activiti.web.form.Node;

@Controller
@RequestMapping(value = "/system/menu")
public class MenuController extends BaseController {
	@Autowired
	private PermissionService permissionService;

	/**
	 * 首页画面
	 * 
	 * @return
	 */
	@RequestMapping(value = "")
	public String mainPage() {
		return "/system/menu";
	}

	@RequestMapping(value = "/getNodes")
	@ResponseBody
	public List<Node> getNodes(String id) {
		List<Permission> permissions = permissionService.getByParent(id);
		if (null == permissions)
			return null;
		List<Node> nodes = new ArrayList<Node>(permissions.size());
		for (Permission p : permissions) {
			Node n = new Node();
			n.setId(p.getId());
			n.setCode(p.getCode());
			n.setName(p.getName());
			nodes.add(n);
		}
		return nodes;
	}

	@RequestMapping(value = "/getNode/{id}")
	@ResponseBody
	public Node getNode(@PathVariable String id) {
		Permission p = permissionService.get(id);
		if (null == p)
			return null;
		Node n = new Node();
		n.setId(p.getId());
		n.setCode(p.getCode());
		n.setName(p.getName());
		return n;
	}

	/**
	 * 获取当前可配置菜单
	 * 
	 * @return
	 */
	@RequestMapping(value = "/pages")
	@ResponseBody
	public Collection<Page> getAvaliblePage() {
		Map<String, Page> pageMap = PermissionManager.getInstance()
				.getMenumap();
		Collection<Page> collection = new ArrayList<Page>();
		for (Page p : pageMap.values()) {
			if (!p.isUsed()) {
				collection.add(p);
			}
		}
		return collection;
	}

	/**
	 * 获取当前可配置菜单
	 * 
	 * @return
	 */
	@RequestMapping(value = "/savePage")
	@ResponseBody
	public String savePage(Page page) {
		Permission p = null;
		// 新增模式
		if (Utils.isEmpty(page.getId())) {
			p = new Permission();
			if (null != page.getPid() && page.getPid().length() > 0) {
				Permission pp = permissionService.get(page.getPid());
				if (null != pp) {
					p.setParent(pp);
				}
			}
		} else {
			p = permissionService.get(page.getId());
		}
		p.setCode(page.getCode());
		p.setName(page.getName());
		permissionService.save(p);
		PermissionManager.getInstance().usePage(p.getCode());
		return "sucess";
	}

	@RequestMapping(value = "/deletePage")
	@ResponseBody
	public String deletePage(Page page) {
		if (!Utils.isEmpty(page.getId())) {
			permissionService.delete(page.getId());
		}
		return "success";
	}
}
