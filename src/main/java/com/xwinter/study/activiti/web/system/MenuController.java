package com.xwinter.study.activiti.web.system;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xwinter.study.access.Function;
import com.xwinter.study.access.Page;
import com.xwinter.study.access.PermissionManager;
import com.xwinter.study.activiti.common.Utils;
import com.xwinter.study.activiti.entity.system.Permission;
import com.xwinter.study.activiti.service.system.PermissionService;
import com.xwinter.study.activiti.web.BaseController;
import com.xwinter.study.activiti.web.form.system.PermissionForm;

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

	/**
	 * 根据上级ID获取下级功能对象
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getNodes")
	@ResponseBody
	public List<PermissionForm> getNodes(String id) {
		List<Permission> permissions = permissionService.getByParent(id);
		if (null == permissions)
			return null;
		List<PermissionForm> nodes = new ArrayList<PermissionForm>(
				permissions.size());
		for (Permission p : permissions) {
			PermissionForm n = new PermissionForm();
			n.setId(p.getId());
			n.setCode(p.getCode());
			n.setName(p.getName());
			n.setLink(p.getUrl());
			n.setFolder(p.isFolder());
			nodes.add(n);
		}
		return nodes;
	}

	/**
	 * 根据功能ID获取功能详细信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getNode/{id}")
	@ResponseBody
	public PermissionForm getNode(@PathVariable String id) {
		Permission p = permissionService.get(id);
		if (null == p)
			return null;
		PermissionForm n = new PermissionForm();
		n.setId(p.getId());
		n.setCode(p.getCode());
		n.setName(p.getName());
		n.setLink(p.getUrl());
		n.setStatus(p.getStatus());
		n.setFolder(p.isFolder());
		return n;
	}

	/**
	 * 获取当前可配置菜单
	 * 
	 * @return
	 */
	@RequestMapping(value = "/pages")
	@ResponseBody
	public Collection<PermissionForm> getAvaliblePage() {
		Map<String, Page> pageMap = PermissionManager.getInstance()
				.getMenumap();
		Collection<PermissionForm> collection = new ArrayList<PermissionForm>();
		for (Page p : pageMap.values()) {
			PermissionForm form = new PermissionForm();
			form.setCode(p.getCode());
			form.setName(p.getName());
			form.setLink(p.getUrl());
			collection.add(form);
		}
		return collection;
	}

	/**
	 * 获取当前可配置菜单
	 * 
	 * @return
	 */
	@RequestMapping(value = "/functions/{pageCode}")
	@ResponseBody
	public Collection<PermissionForm> getFunctionByPage(
			@PathVariable String pageCode) {
		Page page = PermissionManager.getInstance().getByCode(pageCode);
		Collection<PermissionForm> collection = new ArrayList<PermissionForm>();
		for (Function f : page.getFuns().values()) {
			PermissionForm form = new PermissionForm();
			form.setCode(f.getCode());
			form.setName(f.getName());
			collection.add(form);
		}
		return collection;
	}

	/**
	 * 保存配置表单
	 * 
	 * @return
	 */
	@RequestMapping(value = "/savePage")
	@ResponseBody
	public Map<String, Object> savePage(PermissionForm form) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		Permission p = null;
		// 新增模式
		if (Utils.isEmpty(form.getId())) {
			p = new Permission();
			if (null != form.getPid() && form.getPid().length() > 0) {
				Permission pp = permissionService.get(form.getPid());
				if (null != pp) {
					p.setParent(pp);
				}
			}
			p.setUrl(form.getLink());
			p.setFolder(form.isFolder());
		}
		// 修改模式
		else {
			p = permissionService.get(form.getId());
		}
		p.setCode(form.getCode());
		p.setName(form.getName());
		p.setStatus(form.getStatus());
		permissionService.save(p);
		PermissionManager.getInstance().usePage(p.getCode());
		resMap.put("success", true);
		return resMap;
	}

	/**
	 * 删除表单
	 */
	@RequestMapping(value = "/deletePage")
	@ResponseBody
	public Map<String, Object> deletePage(Page page) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		if (!Utils.isEmpty(page.getId())) {
			permissionService.delete(page.getId());
		}
		resMap.put("success", true);
		return resMap;
	}
}
