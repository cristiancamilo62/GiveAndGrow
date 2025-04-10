package com.giveandgrow.domain.model.user;

import com.giveandgrow.shared.helper.ObjectHelper;
import com.giveandgrow.shared.helper.TextHelper;
import com.giveandgrow.shared.helper.UuidHelper;
import lombok.Getter;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Getter
@Service
public class UserDomain {

	private UUID id;

	private String identification;

	private String firstName;

	private String middleName;

	private String lastName;

	private String middleLastName;

	private String email;

	private boolean confirmedEmail;

	private String phoneNumber;

	private boolean confirmedPhoneNumber;

	private String password;

	private boolean accountStatement;

	private String role;


	public UserDomain() {
		setId(UuidHelper.DEFAULT_UUID);
        setIdentification(TextHelper.EMPTY);
        setFirstName(TextHelper.EMPTY);
        setMiddleName(TextHelper.EMPTY);
        setLastName(TextHelper.EMPTY);
        setMiddleLastName(TextHelper.EMPTY);
        setEmail(TextHelper.EMPTY);
        setConfirmedEmail(false);
        setPhoneNumber(TextHelper.EMPTY);
        setConfirmedPhoneNumber(false);
        setPassword(TextHelper.EMPTY);
        setAccountStatement(false);
        setRole(TextHelper.EMPTY);
	}




	public UserDomain(UUID id, String identification, String firstName, String middleName, String lastName,
					  String middleLastName, String email, boolean confirmedEmail, String phoneNumber,
					  boolean confirmedPhoneNumber, String password,
					  boolean accountStatement, String role) {
		setId(id);
		setIdentification(identification);
		setFirstName(firstName);
		setMiddleName(middleName);
		setLastName(lastName);
		setMiddleLastName(middleLastName);
		setEmail(email);
		setConfirmedEmail(confirmedEmail);
		setPhoneNumber(phoneNumber);
		setConfirmedPhoneNumber(confirmedPhoneNumber);
		setPassword(password);
		setAccountStatement(accountStatement);
		setRole(role);
	}

	public final void setId(UUID id) {
		this.id = id;
	}

	public final void setIdentification(String identification) {
		this.identification = ObjectHelper.getDefault(TextHelper.applyTrim(identification), TextHelper.EMPTY);
	}

	public final void setFirstName(String firstName) {
		this.firstName = ObjectHelper.getDefault(TextHelper.applyTrim(firstName), TextHelper.EMPTY);
	}

	public final void setMiddleName(String middleName) {
		this.middleName = ObjectHelper.getDefault(TextHelper.applyTrim(middleName), TextHelper.EMPTY);
	}

	public final void setLastName(String lastName) {
		this.lastName = ObjectHelper.getDefault(TextHelper.applyTrim(lastName), TextHelper.EMPTY);
	}

	public final void setMiddleLastName(String middleLastName) {
		this.middleLastName = ObjectHelper.getDefault(TextHelper.applyTrim(middleLastName), TextHelper.EMPTY);
	}

	public final void setEmail(String email) {
		this.email = ObjectHelper.getDefault(TextHelper.applyTrim(email), TextHelper.EMPTY);
	}

	public final void setConfirmedEmail(boolean confirmedEmail) {
		this.confirmedEmail = ObjectHelper.getDefault(confirmedEmail, false);
	}

	public final void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = ObjectHelper.getDefault(TextHelper.applyTrim(phoneNumber), TextHelper.EMPTY);
	}

	public final void setConfirmedPhoneNumber(boolean confirmedPhoneNumber) {
		this.confirmedPhoneNumber = ObjectHelper.getDefault(confirmedPhoneNumber, false);
	}

	public final void setPassword(String password) {
		this.password = ObjectHelper.getDefault(TextHelper.applyTrim(password), TextHelper.EMPTY);
	}

	public final void setAccountStatement(boolean accountStatement) {
		this.accountStatement = ObjectHelper.getDefault(accountStatement, false);
	}

	public final void setRole(String role) {
		this.role = ObjectHelper.getDefault(TextHelper.applyTrim(role), TextHelper.EMPTY);
	}
}
