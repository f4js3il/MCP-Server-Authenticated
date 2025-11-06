package com.mcp.newmcp.service;


import org.springaicommunity.mcp.annotation.McpTool;
import org.springaicommunity.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Component;

@Component
public class PersonalInfoService {


    @McpTool(name = "john-info", description = "get information about john")
    public String getInfo(@McpToolParam(description = "kids info (default: false)",
            required = false) Boolean kids){
        kids = kids != null && kids;
        String info = "John is working as an accountant in Mckinsey. He is a smarty pants";
        return info + (kids ? " He has 5 kids" : "");
        
    }
}
