package amazon;

import java.util.concurrent.CountDownLatch;

import com.ui4j.api.browser.PageConfiguration;
import com.ui4j.api.interceptor.Interceptor;
import com.ui4j.api.interceptor.Request;
import com.ui4j.api.interceptor.Response;

public class MyPageConfiguration extends PageConfiguration {

	private static CountDownLatch latch;

	private static MyPageConfiguration instance = new MyPageConfiguration();
	
	public static MyPageConfiguration instance() {
		return instance;
		
	}
	private MyPageConfiguration() {
		super(getIntercepor());
		latch = new CountDownLatch(1);
	}
	
	public void waitFinish() {
		try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
		latch = new CountDownLatch(1);
	}
	
	
	private static Interceptor getIntercepor() {
		return new Interceptor() {

            @Override
            public void beforeLoad(Request request) {
                
            }
            
            @Override
            public void afterLoad(Response response) {
                    latch.countDown();
            }
        };			
	}
}
