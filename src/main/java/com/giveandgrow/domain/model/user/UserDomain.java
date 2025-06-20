package com.giveandgrow.domain.model.user;

import com.giveandgrow.domain.model.security.Role;
import com.giveandgrow.shared.helper.ObjectHelper;
import com.giveandgrow.shared.helper.TextHelper;
import com.giveandgrow.shared.helper.UuidHelper;
import lombok.Data;
import lombok.Getter;
import java.util.UUID;

@Getter
@Data
public class UserDomain {

	private UUID id;

	private String identification;

	private String firstName;

	private String middleName;

	private String lastName;

	private String middleLastName;

	private String email;

	private String institution;

	private String phoneOfReference;

	private boolean confirmedEmail;

	private String phoneNumber;

	private boolean confirmedPhoneNumber;

	private String password;

	private boolean accountStatement;

	private final Role role;


	public UserDomain() {
		setId(UuidHelper.DEFAULT_UUID);
        setIdentification(TextHelper.EMPTY);
        setFirstName(TextHelper.EMPTY);
        setMiddleName(TextHelper.EMPTY);
        setLastName(TextHelper.EMPTY);
        setMiddleLastName(TextHelper.EMPTY);
        setEmail(TextHelper.EMPTY);
		setInstitution(TextHelper.EMPTY);
		setPhoneOfReference(TextHelper.EMPTY);
        setConfirmedEmail(false);
        setPhoneNumber(TextHelper.EMPTY);
        setConfirmedPhoneNumber(false);
        setPassword(TextHelper.EMPTY);
        setAccountStatement(false);
        this.role = Role.USER;
	}

	public UserDomain(UUID id, String identification, String firstName, String middleName, String lastName,
					  String middleLastName, String email,String institution, String phoneOfReference, boolean confirmedEmail, String phoneNumber,
					  boolean confirmedPhoneNumber, String password,
					  boolean accountStatement) {
		setId(id);
		setIdentification(identification);
		setFirstName(firstName);
		setMiddleName(middleName);
		setLastName(lastName);
		setMiddleLastName(middleLastName);
		setEmail(email);
		setInstitution(institution);
		setPhoneOfReference(phoneOfReference);
		setConfirmedEmail(confirmedEmail);
		setPhoneNumber(phoneNumber);
		setConfirmedPhoneNumber(confirmedPhoneNumber);
		setPassword(password);
		setAccountStatement(accountStatement);
		this.role = Role.USER;
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

	public final void setInstitution(String institution){
		this.institution = ObjectHelper.getDefault(TextHelper.applyTrim(institution), TextHelper.EMPTY);
	}

	public final void setPhoneOfReference(String phoneOfReference){
		this.phoneOfReference = ObjectHelper.getDefault(TextHelper.applyTrim(phoneOfReference), TextHelper.EMPTY);
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

}
