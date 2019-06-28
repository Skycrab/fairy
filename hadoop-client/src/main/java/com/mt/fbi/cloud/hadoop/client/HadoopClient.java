package com.mt.fbi.cloud.hadoop.client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;


/**
 * Created by yihaibo on 2019-04-28.
 */
public final class HadoopClient {
    /**
     * hadoop fs get功能
     * @param srcPath
     * @param dstPath
     * @throws Exception
     */
    public static void copyToLocal(String srcPath, String dstPath) throws Exception {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        fs.copyToLocalFile(false, new Path(srcPath), new Path(dstPath), true);
    }

    private HadoopClient() {
    }
}
