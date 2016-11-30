package course.dv.webmd.model;

public class Answer implements Comparable<Answer> {

	private String answerId;
	private String questionId;
	private String answerQuestionURL;
	private String answerMemberId;
	private String answerContent;
	private String answerPostDate;
	private String answerHelpfulNum;
	private String answerVoteNum;
	
	public String getAnswerVoteNum() {
		return answerVoteNum;
	}
	public void setAnswerVoteNum(String answerVoteNum) {
		this.answerVoteNum = answerVoteNum;
	}
	public String getAnswerId() {
		return answerId;
	}
	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getAnswerQuestionURL() {
		return answerQuestionURL;
	}
	public void setAnswerQuestionURL(String answerQuestionURL) {
		this.answerQuestionURL = answerQuestionURL;
	}
	public String getAnswerMemberId() {
		return answerMemberId;
	}
	public void setAnswerMemberId(String answerMemberId) {
		this.answerMemberId = answerMemberId;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	public String getAnswerPostDate() {
		return answerPostDate;
	}
	public void setAnswerPostDate(String answerPostDate) {
		this.answerPostDate = answerPostDate;
	}
	public String getAnswerHelpfulNum() {
		return answerHelpfulNum;
	}
	public void setAnswerHelpfulNum(String answerHelpfulNum) {
		this.answerHelpfulNum = answerHelpfulNum;
	}
	@Override
	public int compareTo(Answer o) {
		if(o.answerVoteNum.compareTo(this.answerVoteNum)>0)return -1;
		else return 1;
	}
}
