package com.giveandgrow.shared.messages;

import com.giveandgrow.shared.exception.custom.SharedGiveAndGrowException;
import com.giveandgrow.shared.helper.ObjectHelper;
import com.giveandgrow.shared.messages.enums.MessageCategory;
import com.giveandgrow.shared.messages.enums.MessageCode;
import com.giveandgrow.shared.messages.enums.MessageType;
import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Builder
@Getter
public class MessageCatalog {
    
    private static final Map<MessageCode, Message>  MESSAGES = new HashMap<MessageCode, Message>();

    static  {
    	uploadMessages();
    }

    private MessageCatalog() {
        super();
    }
    
    public static void uploadMessages() {

		addMessage(Message.create(MessageCode.M0000001, MessageType.TECHNICAL, MessageCategory.ERROR,
				"It is not possible to obtain a message with an empty or null code. The process cannot continue..."));

		addMessage(Message.create(MessageCode.M0000002, MessageType.TECHNICAL, MessageCategory.ERROR,
				"There is no message with the indicated code. The process cannot continue..."));

		addMessage(Message.create(MessageCode.M0000003, MessageType.USER, MessageCategory.ERROR,
				"An unexpected problem occurred while trying to carry out the desired operation. Please try again and if the problem persists, contact the application administrator."));

		addMessage(Message.create(MessageCode.M0000004, MessageType.USER, MessageCategory.ERROR,
				"cannot be null or empty"));

		addMessage(Message.create(MessageCode.M0000005, MessageType.USER, MessageCategory.ERROR,
				"must contain only digits"));

		addMessage(Message.create(MessageCode.M0000006, MessageType.USER, MessageCategory.ERROR,
				"must contain only letters or digits"));

		addMessage(Message.create(MessageCode.M0000007, MessageType.USER, MessageCategory.ERROR,
				"must be in the format 'DD/MM/YYYY'"));

		addMessage(Message.create(MessageCode.M0000008, MessageType.USER, MessageCategory.ERROR,
				"must be in the format name@domain.com"));

		addMessage(Message.create(MessageCode.M0000009, MessageType.USER, MessageCategory.ERROR,
				"must contain at least one of ['0-9', 'A-Z', 'a-z', '@$!%*?&']"));

		addMessage(Message.create(MessageCode.M0000010, MessageType.USER, MessageCategory.ERROR,
				"must have a length between "));

		addMessage(Message.create(MessageCode.M0000011, MessageType.USER, MessageCategory.ERROR,
				"must have a length of "));

		addMessage(Message.create(MessageCode.M0000012, MessageType.TECHNICAL, MessageCategory.ERROR,
				"is the default value."));

		addMessage(Message.create(MessageCode.M0000013, MessageType.USER, MessageCategory.ERROR,
				"A client with the same email already exists."));

		addMessage(Message.create(MessageCode.M0000014, MessageType.TECHNICAL, MessageCategory.ERROR,
				"A client with the same identifier already exists. UUID generator created an existing customer ID"));

		addMessage(Message.create(MessageCode.M0000015, MessageType.USER, MessageCategory.ERROR,
				"A client with the same identification number already exists."));

		addMessage(Message.create(MessageCode.M0000016, MessageType.TECHNICAL, MessageCategory.ERROR,
				" id does not exist in the database."));

		addMessage(Message.create(MessageCode.M0000017, MessageType.USER, MessageCategory.ERROR,
				"An unexpected error occurred."));

		addMessage(Message.create(MessageCode.M0000018, MessageType.USER, MessageCategory.ERROR,
				"Organization contact number already exists"));

		addMessage(Message.create(MessageCode.M0000019, MessageType.USER, MessageCategory.ERROR,
				"Organization email already exists"));

		addMessage(Message.create(MessageCode.M0000020, MessageType.USER, MessageCategory.ERROR,
				"must contain only letters, digits and spaces"));


		

	}
    
    private static void addMessage(final Message message) {
		MESSAGES.put(message.getCode(), message);
	}
    
    private static Message getMessage(final MessageCode code) {
		if(ObjectHelper.isNull(code)) {
			var messageUser = getContentMessage(MessageCode.M0000003);
			var messageTechnical = getContentMessage(MessageCode.M0000001);
			throw new SharedGiveAndGrowException(messageUser,messageTechnical);
		}
		
		if(!MESSAGES.containsKey(code)) {
			var messageUser = getContentMessage(MessageCode.M0000003);
			var messageTechnical = getContentMessage(MessageCode.M0000002);
			throw new SharedGiveAndGrowException(messageTechnical,messageUser);
		}
		
		return MESSAGES.get(code);
	}
	
	public static String getContentMessage(final MessageCode code) {
		return getMessage(code).getContained();
	}



}
