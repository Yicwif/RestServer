package com.asiainfo.ocmanager.rest.resource.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.asiainfo.ocmanager.persistence.mapper.ServiceInstanceMapper;
import com.asiainfo.ocmanager.persistence.model.ServiceInstance;
import com.asiainfo.ocmanager.persistence.DBConnectorFactory;

/**
 * 
 * @author zhaoyim
 *
 */
public class ServiceInstancePersistenceWrapper {

	/**
	 * 
	 * @param tenantId
	 * @return
	 */
	public static List<ServiceInstance> getServiceInstancesInTenant(String tenantId) {
		SqlSession session = DBConnectorFactory.getSession();
		List<ServiceInstance> serviceInstances = new ArrayList<ServiceInstance>();
		try {
			ServiceInstanceMapper mapper = session.getMapper(ServiceInstanceMapper.class);

			serviceInstances = mapper.selectServiceInstancesByTenant(tenantId);

			session.commit();
		} catch (Exception e) {
			session.rollback();
			throw e;
		} finally {
			session.close();
		}

		return serviceInstances;
	}

	/**
	 * 
	 * @param tenant
	 */
	public static void createServiceInstance(ServiceInstance serviceInstance) {
		SqlSession session = DBConnectorFactory.getSession();
		try {
			ServiceInstanceMapper mapper = session.getMapper(ServiceInstanceMapper.class);

			mapper.insertServiceInstance(serviceInstance);

			session.commit();
		} catch (Exception e) {
			session.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	/**
	 * 
	 * @param tenantId
	 * @param instanceName
	 */
	public static void deleteServiceInstance(String tenantId, String instanceName) {
		SqlSession session = DBConnectorFactory.getSession();
		try {
			ServiceInstanceMapper mapper = session.getMapper(ServiceInstanceMapper.class);

			mapper.deleteServiceInstance(tenantId, instanceName);

			session.commit();
		} catch (Exception e) {
			session.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	/**
	 * 
	 * @return
	 */
	public static List<ServiceInstance> getAllServiceInstances() {
		SqlSession session = DBConnectorFactory.getSession();
		List<ServiceInstance> serviceInstances = new ArrayList<ServiceInstance>();
		try {
			ServiceInstanceMapper mapper = session.getMapper(ServiceInstanceMapper.class);

			serviceInstances = mapper.selectAllServiceInstances();

			session.commit();
		} catch (Exception e) {
			session.rollback();
			throw e;
		} finally {
			session.close();
		}

		return serviceInstances;
	}

}
