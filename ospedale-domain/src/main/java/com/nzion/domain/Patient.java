package com.nzion.domain;

import com.nzion.domain.annot.AccountNumberField;
import com.nzion.domain.pms.IsGuarantor;
import com.nzion.enums.CommunicationPreference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PATIENT")
@AccountNumberField
public class Patient extends Person implements IsGuarantor {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    private String civilId;

    private String afyaId;

    private boolean veterian;

    private Enumeration race;

    private Enumeration occupation;

    private Enumeration religion;

    private Enumeration gender;

    private Enumeration ethnicity;

    private Enumeration socioEconomic;

    private DataResource patientImage;

    private Date firstVisit;

    private Date lastVisit;

    private Set<Notes> notes;

    private String causeOfDeath;

    private Enumeration employeeStatus;

    private CommunicationPreference remainderPreference;

    private String placeOfDeath;

    private boolean sendSms;

    private String patientType;

    private String nationality;

    private String bloodGroup;

    private String rh;
    
    private Set<PatientInsurance> patientInsurances;
    
    private PatientCorporate patientCorporate;

    private Set<PatientPrivacyPolicyConsent> patientPrivacyPolicyConsents;
    
    private String passport;
    
    private Date expiryDate;
    
    private String selectionType;
    
    private String staff = "NO";
    
    private String tariffCode;
    private String tariffId;
    private String tariffName;
    
    private String fileNo;
    private String notificationRequired;

    @Transient
    private String registeredFrom;

    
	public String getCivilId() {
        return civilId;
    }

    public void setCivilId(String civilId) {
        this.civilId = civilId;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getRh() {
        return rh;
    }

    public void setRh(String rh) {
        this.rh = rh;
    }

    public String getAfyaId() {
        return afyaId;
    }

    public void setAfyaId(String afyaId) {
        this.afyaId = afyaId;
    }

    @Column(name = "PATIENT_TYPE")
    public String getPatientType() {
        return patientType;
    }

    public void setPatientType(String patientType) {
        this.patientType = patientType;
    }

    public boolean isSendSms() {
        return sendSms;
    }

    public void setSendSms(boolean sendSms) {
        this.sendSms = sendSms;
    }

    public String getPlaceOfDeath() {
        return placeOfDeath;
    }

    public void setPlaceOfDeath(String placeOfDeath) {
        this.placeOfDeath = placeOfDeath;
    }

    @OneToOne(targetEntity = Enumeration.class)
    @JoinColumn(name = "EMPLOYEE_STATUS_ID")
    public Enumeration getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(Enumeration employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public Patient() {
        super(PartyType.PATIENT);
    }

    static {
        Party.setPartyMap(Patient.class, PartyType.PATIENT);
    }

    @OneToMany(targetEntity = Notes.class, fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "PATIENT_ID", nullable = false)
    @Fetch(FetchMode.SELECT)
    public Set<Notes> getNotes() {
        if (notes == null) notes = new HashSet<Notes>();
        return notes;
    }

    public void setNotes(Set<Notes> notes) {
        this.notes = notes;
    }
    
	public void addNote(Notes note) {
        getNotes().add(note);
    }
	
	
	@OneToMany(targetEntity = PatientInsurance.class, fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "PATIENT_ID", nullable = false)
    @Fetch(FetchMode.SELECT)
    @OrderBy("createdTxTimestamp desc")
	public Set<PatientInsurance> getPatientInsurances() {
		if(patientInsurances == null) patientInsurances = new HashSet<PatientInsurance>();
		return patientInsurances;
	}

	public void setPatientInsurances(Set<PatientInsurance> patientInsurances) {
		this.patientInsurances = patientInsurances;
	}

    @OneToMany(targetEntity = PatientPrivacyPolicyConsent.class, fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "PATIENT_ID", nullable = false)
    @Fetch(FetchMode.SELECT)
    public Set<PatientPrivacyPolicyConsent> getPatientPrivacyPolicyConsents() {
        return patientPrivacyPolicyConsents;
    }

    public void setPatientPrivacyPolicyConsents(Set<PatientPrivacyPolicyConsent> patientPrivacyPolicyConsents) {
        this.patientPrivacyPolicyConsents = patientPrivacyPolicyConsents;
    }

    public void addPatientInsurance(PatientInsurance patientInsurance){
		getPatientInsurances().add(patientInsurance);
	}

    @OneToOne(targetEntity = Enumeration.class)
    @JoinColumn(name = "ETHNICITY")
    public Enumeration getEthnicity() {
        return ethnicity;
    }

    @Column(name = "FIRST_VISIT")
    @Temporal(TemporalType.DATE)
    public Date getFirstVisit() {
        return firstVisit;
    }

    @Column(name = "LAST_VISIT")
    @Temporal(TemporalType.DATE)
    public Date getLastVisit() {
        return lastVisit;
    }



    @OneToOne(targetEntity = Enumeration.class)
    @JoinColumn(name = "OCCUPATION_ID")
    public Enumeration getOccupation() {
        return occupation;
    }

    @OneToOne(targetEntity = Enumeration.class)
    @JoinColumn(name = "RACE_ID")
    public Enumeration getRace() {
        return race;
    }

    @OneToOne(targetEntity = Enumeration.class)
    @JoinColumn(name = "RELIGION_ID")
    public Enumeration getReligion() {
        return religion;
    }

    @Column(name = "IS_VETERIAN")
    public boolean isVeterian() {
        return veterian;
    }

    public void setEthnicity(Enumeration ethnicity) {
        this.ethnicity = ethnicity;
    }

    public void setFirstVisit(Date firstVisit) {
        this.firstVisit = firstVisit;
    }

    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }


    public void setOccupation(Enumeration occupation) {
        this.occupation = occupation;
    }

    public void setRace(Enumeration race) {
        this.race = race;
    }

    public void setReligion(Enumeration religion) {
        this.religion = religion;
    }

    public void setVeterian(boolean veterian) {
        this.veterian = veterian;
    }

    @OneToOne(targetEntity = Enumeration.class)
    @JoinColumn(name = "SOCIOECONOMIC_ID")
    public Enumeration getSocioEconomic() {
        return socioEconomic;
    }

    public void setSocioEconomic(Enumeration socioEconomic) {
        this.socioEconomic = socioEconomic;
    }

    @OneToOne(fetch = FetchType.LAZY, targetEntity = DataResource.class)
    @Cascade(value = { CascadeType.ALL })
    @JoinColumn(name = "PATIENT_IMAGE")
    public DataResource getPatientImage() {
        return patientImage;
    }

    public void setPatientImage(DataResource patientImage) {
        this.patientImage = patientImage;
    }

    @Column(name = "CAUSE_OF_DEATH")
    public String getCauseOfDeath() {
        return causeOfDeath;
    }

    public void setCauseOfDeath(String causeOfDeath) {
        this.causeOfDeath = causeOfDeath;
    }
    
    @OneToOne(fetch = FetchType.EAGER, targetEntity = PatientCorporate.class)
    @Cascade(value = { CascadeType.ALL })
    public PatientCorporate getPatientCorporate() {
		return patientCorporate;
	}

	public void setPatientCorporate(PatientCorporate patientCorporate) {
		this.patientCorporate = patientCorporate;
	}

    public String getNotificationRequired() {
        return notificationRequired;
    }

    public void setNotificationRequired(String notificationRequired) {
        this.notificationRequired = notificationRequired;
    }

    @Override
    public String toString() {
        StringBuilder br = new StringBuilder();
		if(super.getSalutation() != null)
			br.append(this.getSalutation()+".");
		if(super.getFirstName() != null)
			br.append(this.getFirstName());
		if(super.getMiddleName() != null)
			br.append(" "+this.getMiddleName());
		if(super.getLastName() != null)
			br.append(" "+this.getLastName());
		if(super.getEndMostName() != null)
			br.append(" "+this.getEndMostName());
		return br.toString();
    }

    @Enumerated(EnumType.STRING)
    public CommunicationPreference getRemainderPreference() {
        return remainderPreference;
    }

    public void setRemainderPreference(CommunicationPreference remainderPreference) {
        this.remainderPreference = remainderPreference;
    }

    @Override
    @Transient
    public Enumeration getGender() {
        return gender;
    }

    public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getSelectionType() {
		return selectionType;
	}

	public void setSelectionType(String selectionType) {
		this.selectionType = selectionType;
	}

	@Column(name = "EXPIRY_DATE")
    @Temporal(TemporalType.DATE)
	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
    public void setGender(Enumeration gender) {
        this.gender = gender;
    }

	public String getStaff() {
		return staff;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}

	public String getTariffCode() {
		if(tariffCode == null || tariffCode.isEmpty())
			return "00";
		return tariffCode;
	}

	public void setTariffCode(String tariffCode) {
		this.tariffCode = tariffCode;
	}
    @Transient
    public String getTariffId() {
        return tariffId;
    }

    public void setTariffId(String tariffId) {
        this.tariffId = tariffId;
    }

    @Transient
    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

    @Transient
    public String getRegisteredFrom() {return registeredFrom;}
    public void setRegisteredFrom(String registeredFrom) {this.registeredFrom = registeredFrom;}
	
}