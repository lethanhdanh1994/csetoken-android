package sg.cse.wallet.api;

import android.content.Context;

import sg.cse.wallet.base.BaseConnectionView;

/**
 * Created by thongphm on 2018.05.18
 * <p>
 * Last modified on 2018.05.21
 * <p>
 * All right reserved
 */
public abstract class BaseAPI {

    protected APIInterface apiInterface;
    protected APIInterface apiInterfaceHeader;

    protected BaseAPI() {
        APIClient.Builder apiClient = new APIClient.Builder();
        apiClient.init();
        apiInterface = apiClient.getClient().create(APIInterface.class);
        apiInterfaceHeader = apiClient.getClientWithHeader().create(APIInterface.class);
    }
}
