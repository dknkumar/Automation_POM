package com.sap.cmoplatform.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static java.util.Base64.getEncoder;

public class IdentityServiceHttpReq {

   private static final Logger LOGGER = LogManager.getLogger(new Object(){}.getClass().getEnclosingClass().getName());

    public static void main(String[] args) {
//        registerUser("https://int.sap.hana.ondemand.com/w6cd17e00");
//        registerUser("https://eu1.hana.ondemand.com/a629a2553");
   }

   private static void registerUser(String spName) {
      String ursUrl = "https://cmo.accounts.ondemand.com/service/users";

      String sUserName = "Selenium";
      String sUserSurname = "Test";
      String sUserEmail = "selenium@test.com";
      String sUserDetails = "name_id=" + sUserEmail
              + "&email=" + sUserEmail
              + "&sp_name=" + spName
              + "&spCustomAttribute5=" + "PNMARKETAUTHO20160811"
              + "&first_name=" + sUserName
              + "&last_name=" + sUserSurname
              + "&login_name=" + sUserEmail
              + "&send_email=" + "false";

      byte[] userDetailBytes = new byte[0];
      userDetailBytes = sUserDetails.getBytes(StandardCharsets.UTF_8);

      HttpPost httpPost = new HttpPost(ursUrl);
      httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
      httpPost.setHeader("AUTHORIZATION", "Basic " + getEncodedAuth());
      HttpEntity fileAsByteArray = new ByteArrayEntity(userDetailBytes);
      httpPost.setEntity(fileAsByteArray);
      HttpResponse response = executeReq(httpPost);

      assert response != null;
      printResponseBody(response);
   }

   private static String getEncodedAuth() {
      String username = "T000008";
      String password = "#Password1";
      byte[] bytes = (username + ":" + password).getBytes(StandardCharsets.UTF_8);
      return getEncoder().encodeToString(bytes);
   }

   @SuppressWarnings("unchecked")
   public static void updateUser() {
      String id = "P000675";
      String scimUrl = "https://cmo.accounts.ondemand.com/service/scim/Users/" + id;

      JSONObject user = new JSONObject();
      JSONObject group = new JSONObject();
      group.put("value", "CMO_ONBOARD");
      JSONArray groups = new JSONArray();
      groups.add(group);
      user.put("id", id);
      user.put("groups", groups);
      user.put("password", "#Password1");
      user.put("active", true);
      user.put("passwordStatus", "enabled");
      user.put("sendMail", "false");
      user.put("mailVerified", "true");

      String jsonData = user.toString();
      HttpPut httpPut = preparePutRequest(scimUrl, jsonData);

      HttpResponse response = executeReq(httpPut);

      assert response != null;
      response.getStatusLine().getStatusCode();
   }

   private static void deleteUser(String id) {
      String url = "https://cmo.accounts.ondemand.com/service/scim/Users/" + id;
      HttpDelete delete = (HttpDelete) prepareRequest(new HttpDelete(url));
      executeReq(delete);
   }

   private static HttpPut preparePutRequest(String scimUrl, String jsonData) {
      HttpPut put = (HttpPut) prepareRequest(new HttpPut(scimUrl));
      try {
         put.setEntity(new StringEntity(jsonData));
      } catch (UnsupportedEncodingException e) {
         LOGGER.error("error while encoding api url : " + e);
      }
      return put;
   }

   private static String getUserId(HttpResponse response) {
      try {
         return getUserIdFromResponse(response);
      } catch (IOException | ParseException e) {
         e.printStackTrace();
      }
      return null;
   }

   private static String getUserIdFromResponse(HttpResponse response) throws IOException, ParseException {
      String userId;
      StringBuilder result = new StringBuilder();
      String line;
      BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
      while ((line = reader.readLine()) != null) {
         result.append(line);
      }
      JSONParser parser = new JSONParser();
      JSONObject object = (JSONObject) parser.parse(result.toString());
      userId = (String) object.get("id");
      return userId;
   }

   private static HttpRequestBase prepareRequest(HttpRequestBase httpRequestBase) {
      httpRequestBase.setHeader("AUTHORIZATION", "Basic " + getEncodedAuth());
      httpRequestBase.setHeader("Content-Type", "application/scim+json");
      return httpRequestBase;
   }

   private static HttpResponse executeReq(HttpRequestBase httpRequest) {
      try {
         return executeHttpRequest(httpRequest);
      } catch (IOException e) {
         LOGGER.error("ioException occured while sending http request : " + e);
      } catch (Exception e) {
         LOGGER.error("exception occured while sending http request : " + e);
      } finally {
         httpRequest.releaseConnection();
      }
      return null;
   }

   private static HttpResponse executeHttpRequest(HttpRequestBase httpRequest) throws IOException {
      HttpClient client = HttpClientBuilder.create().build();
      HttpResponse response = client.execute(httpRequest);
      LOGGER.info("Response Code : " + response.getStatusLine().getStatusCode());
      return response;
   }

   private static void printResponseBody(HttpResponse response) {
      try {
         BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
         String line;
         StringBuilder result = new StringBuilder();
         while ((line = reader.readLine()) != null) {
            result.append(line);
         }
         LOGGER.info(result.toString());
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}