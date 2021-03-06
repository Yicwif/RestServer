package com.asiainfo.ocmanager.rest.resource;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.asiainfo.ocmanager.persistence.model.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.asiainfo.ocmanager.rest.bean.AdapterResponseBean;
import com.asiainfo.ocmanager.rest.resource.utils.ServiceInstancePersistenceWrapper;
import com.asiainfo.ocmanager.rest.resource.utils.TURAssignmentPersistenceWrapper;
import com.asiainfo.ocmanager.rest.resource.utils.TenantPersistenceWrapper;
import com.asiainfo.ocmanager.rest.resource.utils.UserRoleViewPersistenceWrapper;
import com.asiainfo.ocmanager.rest.utils.SSLSocketIgnoreCA;
import com.asiainfo.ocmanager.rest.utils.UUIDFactory;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.asiainfo.ocmanager.rest.resource.quotaUtils.quotaQuery;


/**
 *
 * @author yujing2
 *
 */

@Path("/quota")
public class QuotaResource {

  /**
   * Get Hive quota
   *
   * @return Quota object
   */
  @GET
  @Path("hive/{serviceInstanceId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getHiveQuota(@PathParam("serviceInstanceId") String instanceId) {
    Map items = quotaQuery.getHiveQuota(instanceId);

    return Response.ok().entity(items).build();
  }

  /**
   * Get Hdfs quota
   *
   * @param request
   * @return Quota object
   */
  @GET
  @Path("hdfs")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getHdfsQuota(@Context HttpServletRequest request) {
      String path = request.getParameter("path");
      Map quota = quotaQuery.getHdfsQuota(path);

      return Response.ok().entity(quota).build();
  }

  /**
   * Get Yarn quota
   *
   * @return Quota object
   */
  @GET
  @Path("mapreduce/{queuename}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getYarnQuota(@PathParam("queuename") String queuename) {
    Map quota = quotaQuery.getMrQuota(queuename);
    return Response.ok().entity(quota).build();
  }

  /**
   * Get Hbase quota
   *
   * @return Quota object
   */
  @GET
  @Path("hbase/{namespace}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getHbaseQuota(@PathParam("namespace") String namespace) {
    Map  quota = quotaQuery.getHbaseQuota(namespace);
    return Response.ok().entity(quota).build();
  }

  /**
   * Get Hive quota
   *
   * @return Quota object
   */
  @GET
  @Path("kafka/{serviceInstanceId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getKafkaQuota(@PathParam("serviceInstanceId") String instanceId) {
    Map quota = quotaQuery.getKafkaQuota(instanceId);

    return Response.ok().entity(quota).build();
  }

  /**
   * Get GP quota
   *
   * @return Quota object
   */
  @GET
  @Path("greenplum/{serviceInstanceId}/{usrname}/{password}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getGpQuota(@PathParam("serviceInstanceId") String instanceId,@PathParam("usrname")  String username,@PathParam("password")  String password) {
    Map quota = quotaQuery.getGpQuota(instanceId,username,password);
    return Response.ok().entity(quota).build();
  }

  /**
   * Get mongodb quota
   *
   * @return Quota object
   */
  @GET
  @Path("mongodb/{serviceInstanceId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getMongoQuota(@PathParam("serviceInstanceId")  String instanceId) {
    Map quota = quotaQuery.getMongoQuota(instanceId);
    return Response.ok().entity(quota).build();
  }

  /**
   * Get spark quota
   *
   * @return Quota object
   */
  @GET
  @Path("spark/{serviceInstanceId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getSparkQuota(@PathParam("serviceInstanceId")  String instanceId) {
    Map quota = quotaQuery.getSparkQuota(instanceId);
    return Response.ok().entity(quota).build();
  }

}
