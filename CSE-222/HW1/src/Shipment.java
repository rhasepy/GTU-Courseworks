public class Shipment
{
	/**
	* sender name of shipment
	*/
	private String senderName;

	/**
	* receiver name of shipment
	*/
	private String receiverName;

	/**
	* tracking number of shipment
	*/
	private String trackingNumber;

	/**
	* name of branch that has cargo
	*/
	private String whichBranch;


	/**
	* 4 parameter contructor
	* @param sender, sender of cargo
	* @param receiver, receiver of cargo
	* @param tracking, tracking number of cargo
	* @param branch, name of branch that has cargo
	*/
	public Shipment(String sender, String receiver, String tracking, String branch)
	{
		setSender(sender);
		setReceiver(receiver);
		setTracking(tracking);
		setStatus(branch);
	}

	/**
	* setter method to set sender name
	* @param x, be assigned to sender name
	*/
	public void setSender(String x) { senderName = x; }

	/**
	* setter method to set receiver name
	* @param x, be assigned to receiver name
	*/
	public void setReceiver(String x) { receiverName = x; }

	/**
	* setter method tı set tracking number
	* @param x, be assigned to tracking number
	*/
	public void setTracking(String x) { trackingNumber = x; }

	/**
	* @param x, be assigned to cargo status
	*/
	public void setStatus(String x) { whichBranch = x; }

	/**
	* getter method of sender
	* @return sender name
	*/
	public String getSender() { return senderName; }

	/**
	* getter method of receiver name
	* @return receiver name
	*/
	public String getReceiver() { return receiverName; }

	/**
	* getter method of tracking number
	* @return tracking number
	*/
	public String getTracking() { return trackingNumber; }

	/**
	* getter method of tracking number
	* @return traking number
	*/
	public String getBranch() { return whichBranch; }

	/**
	* override toString method
	* @return information about cargo
	*/
	@Override 
	public String toString()
	{
		return "\n------------------------\n" +
				"Tracking Number: " + trackingNumber +
				" -- Sender: " + senderName +
				" -- Receiver: " + receiverName +
				" -- Branch Status: " + whichBranch +
				"\n------------------------\n";
	}

	/**
	* override equals method
	* @param o, taken object and cast shipment
	* @return true, if the taken cargo and this cargo have same tracking number
	* @return true, if the taken cargo and this cargo have not same tracking number
	*/
	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof Shipment))
			return false;

		Shipment test = (Shipment) o;

		return ( this.getTracking().equals(test.getTracking()) );
	}
}