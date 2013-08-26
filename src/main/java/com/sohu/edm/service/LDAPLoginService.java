package com.sohu.edm.service;

import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import com.sohu.edm.tools.Constants;



public class LDAPLoginService {
    //private String URL = "ldap://localhost:389/";
	private static String URL =	Constants.LDAP_SERVER;
    private static String BASEDN = "ou=catalogue,o=test.com";
    private static String FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
    private static LdapContext ctx = null;
    private static Hashtable<String, String> env = null;
    private static Control[] connCtls = null;
   
   
    private static void LDAP_connect(){
        env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY,FACTORY);
        env.put(Context.PROVIDER_URL, URL+BASEDN);//LDAP server
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
//此处若不指定用户名和密码,则自动转换为匿名登录
       
        try{
            ctx = new InitialLdapContext(env,connCtls);
        }catch(javax.naming.AuthenticationException e){
            System.out.println("Authentication faild: "+e.toString());
        }catch(Exception e){
            System.out.println("Something wrong while authenticating: "+e.toString());
        }
    }
   
   
    private static String getUserDN(String email){
        String userDN = "";
       
        LDAP_connect();
       
        try{
               SearchControls constraints = new SearchControls();
               constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
               NamingEnumeration<?> en = ctx.search("", "mail="+email, constraints); //The UID you are going to query,* means all nodes
               if(en == null){
                System.out.println("Have no NamingEnumeration.");
               }
               if(!en.hasMoreElements()){
                System.out.println("Have no element.");
               }
               while (en != null && en.hasMoreElements()){//maybe more than one element
                   Object obj = en.nextElement();
                   if(obj instanceof SearchResult){
                       SearchResult si = (SearchResult) obj;
                       userDN += si.getName();
                       userDN += "," + BASEDN;
                   }
                   else{
                       System.out.println(obj);
                   }
                   System.out.println();
               }
              }catch(Exception e){
               System.out.println("Exception in search():"+e);
              }
       
        return userDN;
    }
   
   
    public static boolean authenricate(String ID,String password){
        boolean valide = false;
        String userDN = getUserDN(ID);
       
        try {
            ctx.addToEnvironment(Context.SECURITY_PRINCIPAL,userDN);
            ctx.addToEnvironment(Context.SECURITY_CREDENTIALS,password);
            ctx.reconnect(connCtls);
            System.out.println(userDN + " is authenticated");
            valide = true;
        }catch (AuthenticationException e) {
            System.out.println(userDN + " is not authenticated");
            System.out.println(e.toString());
            valide = false;
        }catch (NamingException e) {
            System.out.println(userDN + " is not authenticated");
            valide = false;
        }
       
        return valide;
    }
}