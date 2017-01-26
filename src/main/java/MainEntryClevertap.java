import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import com.clevertap.apns.ApnsClient;
import com.clevertap.apns.Notification;
import com.clevertap.apns.NotificationResponse;
import com.clevertap.apns.NotificationResponseListener;
import com.clevertap.apns.clients.ApnsClientBuilder;

public class MainEntryClevertap {

	public static void main(String[] args) {
//		testSendSynchronouse();
		testSendAsync();
	}
	
	private static void testSendAsync() {
		FileInputStream cert;
		try {
			String certPath = "certificates/hakim_maji_apns/170126b_hakim_apns_dev_cert_and_privatekey.p12";
			String appId = "com.crazybach.ios.casinodeluxe";
			String deviceToken = "5e2d89c5276de7bd9205a3926991733e12f1482185be4849752a5c6d3b78a788";
			cert = new FileInputStream(certPath);
			final ApnsClient client = new ApnsClientBuilder()
			        .withDevelopmentGateway()
			        .inAsynchronousMode()
			        .withCertificate(cert)
			        .withPassword("123456")
			        .withDefaultTopic(appId)
			        .build();
			Notification n = new Notification.Builder(deviceToken)
			        .alertBody("Hello").build();
//			NotificationResponse result = client.push(n);
//			System.out.println("push ok, result:");
//			System.out.println(result.toString());
			client.push(n, new NotificationResponseListener() {
				
				@Override
				public void onSuccess(Notification notification) {
					System.out.println("success");					
				}
				
				@Override
				public void onFailure(Notification notification, NotificationResponse response) {
					System.out.println("failure: " + response.toString());
				}
			});
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	private static void testSendSynchronouse()
	{
		FileInputStream cert;
		try {
			String certPath = "certificates/hakim_maji_apns/170126b_hakim_apns_dev_cert_and_privatekey.p12";
			String appId = "com.crazybach.ios.casinodeluxe";
			String deviceToken = "5e2d89c5276de7bd9205a3926991733e12f1482185be4849752a5c6d3b78a788";
			cert = new FileInputStream(certPath);
			final ApnsClient client = new ApnsClientBuilder()
			        .withDevelopmentGateway()
			        .inSynchronousMode()
			        .withCertificate(cert)
			        .withPassword("123456")
			        .withDefaultTopic(appId)
			        .build();
			Notification n = new Notification.Builder(deviceToken)
			        .alertBody("Hello").build();
			NotificationResponse result = client.push(n);
			System.out.println("push ok, result:");
			System.out.println(result.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
