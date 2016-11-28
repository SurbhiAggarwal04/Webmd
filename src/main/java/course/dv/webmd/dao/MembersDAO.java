package course.dv.webmd.dao;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import course.dv.webmd.common.InitiateTransportClient;
import course.dv.webmd.model.Member;

public class MembersDAO {
	private static	TransportClient client = InitiateTransportClient.client;

	/**
	 * Fetches information for member Id passed as parameter.
	 * 
	 * @param memberId
	 * @return Member
	 */
	public static Member getMemberForMemberId(String memberId) {
		SearchResponse response = client.prepareSearch("webmd")
				.setTypes("members2")
				.setSearchType(SearchType.DFS_QUERY_AND_FETCH)
				.setQuery(QueryBuilders.termQuery("memberId", memberId))
				.addField("memberName")
				.addField("memberType")
				.addField("memberHelpfulVotes")
				.addField("memberFollowerNumber")
				.execute()
				.actionGet();
		Member m = new Member();
		for (SearchHit hit : response.getHits()) {
			m.setMemberName(hit.field("memberName").getValue());
			m.setMemberType(hit.field("memberType").getValue());
			int val = 0; 
			try {
				val = Integer.parseInt(hit.field("memberHelpfulVotes").getValue().toString().trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			m.setMemberHelpfulVotes(val);
			int val2 = 0;
			try {
				val2 = Integer.parseInt(hit.field("memberFollowerNumber").getValue().toString().trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			m.setMemberFollowerNumber(val2);
		}
		return m;
	}
}