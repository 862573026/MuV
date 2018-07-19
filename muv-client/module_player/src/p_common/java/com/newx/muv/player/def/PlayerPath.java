package com.newx.muv.player.def;


import com.newx.utils.utilcode.subutil.PathUtils;

import java.io.File;

/**
 * Created by xuzhijian on 2018/4/25 0025.
 * 路径定义
 */

public class PlayerPath {

    public static final String mediaCacheDir = PathUtils.getAppExtCachePath() +"/media";

    public static final String extMovies = PathUtils.getExtMoviesPath() +"/extMovies.text";

    public static final File MEDIA_CACHE_DIR = new File(mediaCacheDir);

}
