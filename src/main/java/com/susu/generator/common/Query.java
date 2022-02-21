package com.susu.generator.common;


import com.susu.generator.constant.Constant;

import java.util.LinkedHashMap;
import java.util.Map;

public class Query extends LinkedHashMap<String, Object> {
    
	private static final long serialVersionUID = 1L;
	//当前页码
    private int page = 1;
    //每页条数
    private int limit = 10;

    public Query(Map<String, Object> params){
        this.putAll(params);

        //分页参数
        String page = (String) params.get(Constant.PAGE);
        String limit = (String) params.get(Constant.LIMIT);
        if (StringUtils.isNotBlank(page)) {
            this.page = Integer.parseInt(page);
        }
        if (StringUtils.isNotBlank(limit)) {
            this.limit = Integer.parseInt(limit);
            this.put("offset", (this.page - 1) * this.limit);
        }

        this.put("page", this.page);
        this.put("limit", limit);
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
