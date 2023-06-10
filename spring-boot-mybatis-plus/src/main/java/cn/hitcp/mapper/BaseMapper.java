package cn.hitcp.mapper;


import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.annotations.Param;

/**
 * @author Shaoyu Liu
 * @date 2023/5/6 23:14
 */
public interface BaseMapper<T> extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T> {
    default LambdaQueryChainWrapper<T> lambdaQuery() {
        return ChainWrappers.lambdaQueryChain(this);
    }

    default UpdateChainWrapper<T> update() {
        return ChainWrappers.updateChain(this);
    }

    default LambdaUpdateChainWrapper<T> lambdaUpdate() {
        return ChainWrappers.lambdaUpdateChain(this);
    }

    // TODO
//    default void saveBatch(List<T> list) {
//        if (CollectionUtils.isNotEmpty(list)) {
//            list.forEach(this::insert);
//        }
//
//    }

    // TODO
//    default void updateBatchById(List<T> list) {
//        if (CollectionUtils.isNotEmpty(list)) {
//            list.forEach(this::updateById);
//        }
//
//    }

    /**
     * 自定义批量插入
     * 如果要自动填充，@Param(xx) xx参数名必须是 list/collection/array 3个的其中之一
     */
    int insertBatch(@Param("list") List<T> list);

    /**
     * 自定义批量更新，条件为主键
     * 如果要自动填充，@Param(xx) xx参数名必须是 list/collection/array 3个的其中之一
     */
    int updateBatch(@Param("list") List<T> list);
}
