public class EmailMessage {
    private String sender;
    private String recipient;
    private String subject;
    private String body;
    private boolean sent;

    private static int totalEmailsSent = 0;

    public EmailMessage(String sender, String recipient) {
        this.sender = sender;
        this.recipient = recipient;
        this.subject = "";
        this.body = "";
        this.sent = false;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean send() {
        if (!sender.isEmpty() && !recipient.isEmpty()) {
            sent = true;
            totalEmailsSent++;
            return true;
        }
        return false;
    }

    public boolean isSent() {
        return sent;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public static int getTotalEmailsSent() {
        return totalEmailsSent;
    }

    public static void resetTotalEmailsSent() {
        totalEmailsSent = 0;
    }
}