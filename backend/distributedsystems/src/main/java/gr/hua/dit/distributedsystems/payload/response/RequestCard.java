package gr.hua.dit.distributedsystems.payload.response;

public class RequestCard {
	
	private int requestId;
	private String roomName;
	private String sender;
	
	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public RequestCard(int requestId, String roomName, String sender) {
		this.requestId = requestId;
		this.roomName = roomName;
		this.sender = sender;
	}
	
	
	
}
