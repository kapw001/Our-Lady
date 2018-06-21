package com.pappayaed.data;

import com.pappayaed.data.local.LocalDataSource;
import com.pappayaed.data.pref.Pref;
import com.pappayaed.data.remote.RemoteDataSource;

/**
 * Created by yasar on 6/3/18.
 */

public interface DataSource extends RemoteDataSource, LocalDataSource, Pref {


}
