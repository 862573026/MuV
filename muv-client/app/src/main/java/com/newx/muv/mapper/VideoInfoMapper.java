package com.newx.muv.mapper;

import com.newx.entity.local.VideoInfo;

import java.util.List;

/**
 * Created by xuzhijian on 2018/5/31 0031.
 * 视频信息的数据库操作
 */

public class VideoInfoMapper extends BaseMapper {


    public void deleteAll() {
        mDaoSession.getVideoInfoDao().deleteAll();
    }

    public void insertAll(List<VideoInfo> list) {
        deleteAll();
        mDaoSession.getVideoInfoDao().insertInTx(list);
    }

    /**
     * 分页查询
     *
     * @param offset
     * @param limit
     */
    public List<VideoInfo> queryAll(int offset, int limit) {
        return mDaoSession.getVideoInfoDao().queryBuilder()
                .offset(offset)
                .limit(limit)
                .list();
    }

    /**
     * 需要马上查
     * @param list
     * @param offset
     * @param limit
     * @return
     */
    public List<VideoInfo> insertAndQueryAll(List<VideoInfo> list, int offset, int limit) {
        insertAll(list);
        return queryAll(offset, limit);
    }


}
