package com.giveandgrow.shared.messages;


import com.giveandgrow.shared.exception.custom.SharedProfileUserException;
import com.giveandgrow.shared.helper.ObjectHelper;
import com.giveandgrow.shared.messages.enums.MessageCategory;
import com.giveandgrow.shared.messages.enums.MessageCode;
import com.giveandgrow.shared.messages.enums.MessageType;
import lombok.Getter;


@Getter
public class Message {

    private MessageCode code;
	private MessageType type;
	private MessageCategory category;
    private String contained;
	
	private Message(final MessageCode code, final MessageType type, final MessageCategory category,
			final String contained) {
		setCode(code);
		setType(type);
		setCategory(category);
		setContained(contained);
	}
	
	public static Message create(final MessageCode code, final MessageType type, final MessageCategory category,
								 final String contained) {
		return new Message(code, type, category, contained);
	}

	private void setCode(final MessageCode code) {
		if(ObjectHelper.isNull(code)) {
			var messageUser = MessageCatalog.getContentMessage(MessageCode.M0000003);
			var messageTechnical = MessageCatalog.getContentMessage(MessageCode.M0000004);
			throw new SharedProfileUserException(messageTechnical,messageUser);
			
		}
		this.code = code;
	}

	private void setType(final MessageType type) {
		this.type = ObjectHelper.getDefault(type, MessageType.USER);
	}

	private void setCategory(final MessageCategory category) {
		this.category = ObjectHelper.getDefault(category, MessageCategory.ERROR);
	}

	private void setContained(final String contained) {
		this.contained = contained;
	}

}
