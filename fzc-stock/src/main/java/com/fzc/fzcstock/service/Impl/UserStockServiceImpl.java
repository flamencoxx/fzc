package com.fzc.fzcstock.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzc.fzcstock.mapper.UserStockMapper;
import com.fzc.fzcstock.model.UserStock;
import com.fzc.fzcstock.service.UserStockService;
import org.springframework.stereotype.Service;

/**
 * @author 11615
 */
@Service
public class UserStockServiceImpl extends ServiceImpl<UserStockMapper, UserStock> implements UserStockService {
}
