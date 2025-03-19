package com.ahq.listeners;

import org.testng.*;


public class invokedMethodListener implements IInvokedMethodListener {
    
    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        checkForNullParameters(iTestResult);
    }
    
    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
    }
    
    private void checkForNullParameters(ITestResult iTestResult) {
        Object[] parameters = iTestResult.getParameters();
//        if (iTestResult.getName().contains("in CRM")) {
//            if (parameters != null && parameters[0] instanceof java.util.LinkedHashMap) {
//                java.util.LinkedHashMap<String, String> paramMap = (java.util.LinkedHashMap<String, String>) parameters[0];
//                String app_reference = paramMap.get("Application_Reference");
//                if (app_reference == null) {
//                    throw new SkipException("Test '" + iTestResult.getName() + "' is skipped because the Application was not completed in the portal.");
//                }
//            }
//        }
    }
}