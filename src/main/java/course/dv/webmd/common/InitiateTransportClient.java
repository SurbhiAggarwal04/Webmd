package course.dv.webmd.common;

import java.net.InetAddress;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

public class InitiateTransportClient {

	public static final TransportClient client = createTSClient();

	private static final TransportClient createTSClient() {
		if(client == null) {
			try {
				Settings settings = Settings.settingsBuilder()
						.put("cluster.name", "webmd-pi-elastic-cluster").build();
				TransportClient client = TransportClient.builder().settings(settings).build()
						.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("pi.asu.edu"), 9300));
				return client;
			} catch (Exception e) {
				return null;
			}
		}
		else return client;
	}
}