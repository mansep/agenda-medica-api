package com.mansep.agenda.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.mansep.agenda.dto.MedicalAppointmentViewDto;

@Entity(name = "medical_appointment_view")
public class MedicalAppointmentView implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "RESERVED_ID")
    private Long reservedId;
    @Column(name = "RESERVED_STATUS")
    private String reservedStatus;
    @Column(name = "RESERVED_PRICE")
    private Long reservedPrice;
    @Column(name = "DOCTOR_RUT")
    private String doctorRut;
    @Column(name = "DOCTOR_ID")
    private Long doctorId;
    @Column(name = "DOCTOR_NAME")
    private String doctorName;
    @Column(name = "DOCTOR_LAST_NAME")
    private String doctorLastName;
    @Column(name = "SCHEDULE")
    private Date schedule;
    @Column(name = "OFFICE_ID")
    private Long officeId;
    @Column(name = "OFFICE_CODE")
    private String officeCode;
    @Column(name = "OFFICE_NAME")
    private String officeName;
    @Column(name = "OFFICE_FLOOR")
    private String officeFloor;
    @Column(name = "SPECIALITY_ID")
    private Long specialityId;
    @Column(name = "SPECIALITY_PRICE")
    private Long specialityPrice;
    @Column(name = "SPECIALITY_CODE")
    private String specialityCode;
    @Column(name = "SPECIALITY_NAME")
    private String specialityName;
    @Column(name = "BUILDING_ID")
    private Long buildingId;
    @Column(name = "BUILDING_NAME")
    private String buildingName;
    @Column(name = "BUILDING_CODE")
    private String buildingCode;
    @Column(name = "CENTER_ID")
    private Long centerId;
    @Column(name = "CENTER_NAME")
    private String centerName;
    @Column(name = "CENTER_CODE")
    private String centerCode;
    @Column(name = "CENTER_ADDRESS")
    private String centerAddress;
    @Column(name = "CENTER_EMAIL")
    private String centerEmail;
    @Column(name = "CENTER_PHONE")
    private String centerPhone;
    @Column(name = "PATIENT_ID")
    private Long patientId;
    @Column(name = "PATIENT_RUT")
    private String patientRut;
    @Column(name = "PATIENT_NAME")
    private String patientName;
    @Column(name = "PATIENT_LAST_NAME")
    private String patientLastName;
    @Column(name = "PATIENT_EMAIL")
    private String patientEmail;

    public MedicalAppointmentView() {
    }

    public MedicalAppointmentView(MedicalAppointmentViewDto mAppointmentView) {
        if (mAppointmentView == null)
            return;
        setId(mAppointmentView.getId());
        setReservedId(mAppointmentView.getReservedId());
        setReservedStatus(mAppointmentView.getReservedStatus());
        setReservedPrice(mAppointmentView.getReservedPrice());
        setDoctorId(mAppointmentView.getDoctorId());
        setDoctorName(mAppointmentView.getDoctorName());
        setDoctorLastName(mAppointmentView.getDoctorLastName());
        setDoctorRut(mAppointmentView.getDoctorRut());
        setSchedule(mAppointmentView.getSchedule());
        setOfficeCode(mAppointmentView.getOfficeCode());
        setOfficeId(mAppointmentView.getOfficeId());
        setOfficeName(mAppointmentView.getOfficeName());
        setOfficeFloor(mAppointmentView.getOfficeFloor());
        setSpecialityCode(mAppointmentView.getSpecialityCode());
        setSpecialityId(mAppointmentView.getSpecialityId());
        setSpecialityPrice(mAppointmentView.getSpecialityPrice());
        setSpecialityName(mAppointmentView.getSpecialityName());
        setBuildingCode(mAppointmentView.getBuildingCode());
        setBuildingId(mAppointmentView.getBuildingId());
        setBuildingName(mAppointmentView.getBuildingName());
        setCenterCode(mAppointmentView.getCenterCode());
        setCenterId(mAppointmentView.getCenterId());
        setCenterName(mAppointmentView.getCenterName());
        setCenterAddress(mAppointmentView.getCenterAddress());
        setCenterPhone(mAppointmentView.getCenterPhone());
        setCenterEmail(mAppointmentView.getCenterEmail());
        setPatientId(mAppointmentView.getPatientId());
        setPatientName(mAppointmentView.getPatientName());
        setPatientLastName(mAppointmentView.getPatientLastName());
        setPatientRut(mAppointmentView.getPatientRut());

    }

    public MedicalAppointmentViewDto toDto() {
        MedicalAppointmentViewDto mAppointment = new MedicalAppointmentViewDto();
        mAppointment.setId(this.getId());
        mAppointment.setReservedId(this.getReservedId());
        mAppointment.setReservedPrice(this.getReservedPrice());
        mAppointment.setReservedStatus(this.getReservedStatus());
        mAppointment.setDoctorId(this.getDoctorId());
        mAppointment.setDoctorName(this.getDoctorName());
        mAppointment.setDoctorLastName(this.getDoctorLastName());
        mAppointment.setDoctorRut(this.getDoctorRut());
        mAppointment.setSchedule(this.getSchedule());
        mAppointment.setOfficeCode(this.getOfficeCode());
        mAppointment.setOfficeId(this.getOfficeId());
        mAppointment.setOfficeName(this.getOfficeName());
        mAppointment.setSpecialityCode(this.getSpecialityCode());
        mAppointment.setSpecialityPrice(this.getSpecialityPrice());
        mAppointment.setSpecialityId(this.getSpecialityId());
        mAppointment.setSpecialityName(this.getSpecialityName());
        mAppointment.setOfficeFloor(this.getOfficeFloor());
        mAppointment.setBuildingCode(this.getBuildingCode());
        mAppointment.setBuildingId(this.getBuildingId());
        mAppointment.setBuildingName(this.getBuildingName());
        mAppointment.setCenterCode(this.getCenterCode());
        mAppointment.setCenterId(this.getCenterId());
        mAppointment.setCenterName(this.getCenterName());
        mAppointment.setCenterAddress(this.getCenterAddress());
        mAppointment.setCenterPhone(this.getCenterPhone());
        mAppointment.setCenterEmail(this.getCenterEmail());
        mAppointment.setPatientId(this.getPatientId());
        mAppointment.setPatientName(this.getPatientName());
        mAppointment.setPatientLastName(this.getPatientLastName());
        mAppointment.setPatientRut(this.getPatientRut());
        return mAppointment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoctorRut() {
        return doctorRut;
    }

    public void setDoctorRut(String doctorRut) {
        this.doctorRut = doctorRut;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }

    public Date getSchedule() {
        return schedule;
    }

    public void setSchedule(Date schedule) {
        this.schedule = schedule;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getOfficeFloor() {
        return officeFloor;
    }

    public void setOfficeFloor(String officeFloor) {
        this.officeFloor = officeFloor;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }

    public Long getCenterId() {
        return centerId;
    }

    public void setCenterId(Long centerId) {
        this.centerId = centerId;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getCenterCode() {
        return centerCode;
    }

    public void setCenterCode(String centerCode) {
        this.centerCode = centerCode;
    }

    public String getCenterAddress() {
        return centerAddress;
    }

    public void setCenterAddress(String centerAddress) {
        this.centerAddress = centerAddress;
    }

    public String getCenterEmail() {
        return centerEmail;
    }

    public void setCenterEmail(String centerEmail) {
        this.centerEmail = centerEmail;
    }

    public String getCenterPhone() {
        return centerPhone;
    }

    public void setCenterPhone(String centerPhone) {
        this.centerPhone = centerPhone;
    }

    public Long getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(Long specialityId) {
        this.specialityId = specialityId;
    }

    public String getSpecialityCode() {
        return specialityCode;
    }

    public void setSpecialityCode(String specialityCode) {
        this.specialityCode = specialityCode;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }

    public Long getReservedId() {
        return reservedId;
    }

    public void setReservedId(Long reservedId) {
        this.reservedId = reservedId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientRut() {
        return patientRut;
    }

    public void setPatientRut(String patientRut) {
        this.patientRut = patientRut;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getReservedStatus() {
        return reservedStatus;
    }

    public void setReservedStatus(String reservedStatus) {
        this.reservedStatus = reservedStatus;
    }

    public Long getReservedPrice() {
        return reservedPrice;
    }

    public void setReservedPrice(Long reservedPrice) {
        this.reservedPrice = reservedPrice;
    }

    public Long getSpecialityPrice() {
        return specialityPrice;
    }

    public void setSpecialityPrice(Long specialityPrice) {
        this.specialityPrice = specialityPrice;
    }
}