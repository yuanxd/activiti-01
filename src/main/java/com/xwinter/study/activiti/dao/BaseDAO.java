package com.xwinter.study.activiti.dao;

import java.io.Serializable;
import java.util.List;

import com.xwinter.study.activiti.entity.BaseEntity;

/**
 * Dao�㹲ͨ����<br>
 * <p>
 * �������ݷ��ʲ㣨@Entityע�⣩�̳д˹�ͨ���࣬ʵ�����ݷ��ʲ�Ĺ�ͨ����
 * 
 * @param <E>
 *            Dao�������Entity����
 * @param <PK>
 *            Entity������������ͣ�һ����{@link Long}
 * @author Ԭ����
 */
public interface BaseDAO<E extends BaseEntity, PK extends Serializable> {
	// ��¼״̬-��Ч
	public static final int RECORD_STATE_VALID = 1;
	// ��¼״̬-�ύ
	public static final int RECORD_STATE_SUBMIT = 5;
	// ��¼״̬-ɾ��
	public static final int RECORD_STATE_DELETE = 9;

	/**
	 * ��ջ�����
	 */
	public void clear();

	/**
	 * �õ�����ʵ��������޹�������
	 * 
	 * @return
	 */
	public int countAll();

	/**
	 * ���ݲ�ѯ���õ���¼����
	 * 
	 * @param hql
	 * @param paramlist
	 * @return
	 */
	public int countAll(String hql, final Object... paramlist);

	/**
	 * �õ���Чʵ��������޹�������
	 * 
	 * @return
	 */
	public int countValidAll();

	/**
	 * ɾ��ʵ�����
	 * 
	 * @paraE entity
	 */
	public void delete(E entity);

	/**
	 * ��������ɾ��ʵ��
	 * 
	 * @param id
	 */
	public void delete(PK id);

	/**
	 * �ж��Ƿ��������Ϊid��ʵ��
	 * 
	 * @param id
	 *            ʵ������
	 * @return boolean �Ƿ����
	 */
	boolean exists(PK id);

	/**
	 * ˢ�»�����
	 */
	public void flush();

	/**
	 * ����������ȡʵ��
	 * 
	 * @param id
	 * @return
	 */
	public E get(PK id);

	/**
	 * ���ݲ�ѯ���ִ�в�ѯ�����з�ҳ��
	 * 
	 * @param hql
	 *            ��ѯ���
	 * @param pn
	 *            ҳ��
	 * @param pageSize
	 *            ÿҳ��¼��
	 * @param paramlist
	 *            �����б�
	 * @return �����
	 */
	public <T> List<T> list(final String hql, final int pn, final int pageSize,
			final Object... paramlist);

	/**
	 * ���ݲ�ѯ���ִ�в�ѯ�����޷�ҳ��
	 * 
	 * @param hql
	 *            ��ѯ���
	 * @param paramlist
	 *            �����б�
	 * @return �����
	 */
	public <T> List<T> listWithParams(final String hql,
			final Object... paramlist);

	/**
	 * �õ�����ʵ��
	 * 
	 * @return
	 */
	public List<E> listAll();

	/**
	 * ����ҳ����ÿҳ��¼���õ�����ʵ��
	 * 
	 * @param pn
	 *            �ڼ�ҳ��¼
	 * @param pageSize
	 *            ÿҳ��¼��
	 * @return
	 */
	public List<E> listAll(int pn, int pageSize);

	/**
	 * �õ�������Чʵ��
	 * 
	 * @return
	 */
	public List<E> listValidAll();

	/**
	 * ����ҳ����ÿҳ��¼���õ�������Чʵ��
	 * 
	 * @param pn
	 *            �ڼ�ҳ��¼
	 * @param pageSize
	 *            ÿҳ��¼��
	 * @return
	 */
	public List<E> listValidAll(int pn, int pageSize);

	/**
	 * ����ʵ�崦��
	 * 
	 * @paraE entity BaseEntity
	 */
	public void merge(E entity);

	/**
	 * ����С��PK�ĵ�һҳ���ݡ����pk��ָ�����൱�ڵ���listAll({@link listAll}
	 * 
	 * @param pk
	 *            ����
	 * @param pn
	 *            ҳ��
	 * @param pageSize
	 *            ÿҳ����
	 * @return
	 */
	public List<E> next(PK pk, int pn, int pageSize);

	/**
	 * ��������pk�ĵ�һҳ���ݣ����������ļ����������ݡ����pk��ָ�����൱�ڵ���listAll({@link listAll}
	 * 
	 * @param pk
	 *            ����
	 * @param pn
	 *            ҳ��
	 * @param pageSize
	 *            ÿҳ����
	 * @return
	 */
	public List<E> pre(PK pk, int pn, int pageSize);

	/**
	 * ����ʵ�崦��
	 * 
	 * @paraE entity BaseEntity
	 * @return ����
	 */
	public PK save(E entity);

	/**
	 * ��������ʵ�崦��
	 * 
	 * @paraE entity BaseEntity
	 */
	public void saveOrUpdate(E entity);

	/**
	 * ����ʵ�崦��
	 * 
	 * @paraE entity BaseEntity
	 */
	public void update(E entity);

	/**
	 * ��һ����һ���
	 * 
	 * @param id
	 *            �������
	 * @param type
	 *            ����
	 * @return Object ���ض���
	 * @throws Exception
	 *             �׳��쳣
	 */
	public E getPreOrNextData(Integer id, int type, String addUser)
			throws Exception;

	/**
	 * ȡ�����һ������
	 * 
	 * @return ���ݶ���
	 * @throws Exception
	 *             �쳣�׳�
	 */
	public E getLastRowData(String addUser) throws Exception;

}
