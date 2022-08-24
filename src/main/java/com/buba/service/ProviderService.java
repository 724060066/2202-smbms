package com.buba.service;

import com.buba.pojo.Provider;

import java.util.List;

/**
 * 供应商管理service接口
 * @author chenrui
 * @version 1.0
 * @description: TODO
 * @date 2022/8/24 08:30
 */
public interface ProviderService {

    /**
     * 查询供应商信息
     * @return
     */
    List<Provider> listProvider();
}
