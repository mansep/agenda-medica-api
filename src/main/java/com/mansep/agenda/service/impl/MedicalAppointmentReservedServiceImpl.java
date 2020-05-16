package com.mansep.agenda.service.impl;

import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mansep.agenda.dto.MedicalAppointmentReservedDto;
import com.mansep.agenda.dto.StatusDto;
import com.mansep.agenda.entity.Configuration;
import com.mansep.agenda.entity.MedicalAppointment;
import com.mansep.agenda.entity.MedicalAppointmentReserved;
import com.mansep.agenda.entity.MedicalAppointmentView;
import com.mansep.agenda.entity.MedicalCenter;
import com.mansep.agenda.entity.User;
import com.mansep.agenda.entity.enums.Role;
import com.mansep.agenda.entity.enums.Status;
import com.mansep.agenda.exception.BadRequestException;
import com.mansep.agenda.exception.NotFoundException;
import com.mansep.agenda.repository.MedicalAppointmentReservedRepository;
import com.mansep.agenda.repository.MedicalAppointmentViewRepository;
import com.mansep.agenda.service.ConfigurationService;
import com.mansep.agenda.service.EmailService;
import com.mansep.agenda.service.MedicalAppointmentReservedService;
import com.mansep.agenda.service.MedicalAppointmentService;
import com.mansep.agenda.service.MedicalCenterService;
import com.mansep.agenda.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service(value = "medicalAppointmentReservedService")
public class MedicalAppointmentReservedServiceImpl implements MedicalAppointmentReservedService {

	@Autowired
	private MedicalAppointmentReservedRepository mAppointmentReservedRepository;

	@Autowired
	private MedicalAppointmentViewRepository mAppointmentViewRepository;

	@Autowired
	private MedicalAppointmentService mAppointmenService;

	@Autowired
	private MedicalCenterService mCenterService;

	@Autowired
	private UserService userService;

	@Autowired
	private ConfigurationService configurationService;

	@Autowired
	private EmailService emailService;

	public List<MedicalAppointmentReserved> findAll() {
		List<MedicalAppointmentReserved> list = new ArrayList<>();
		mAppointmentReservedRepository.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public MedicalAppointmentReserved findOne(MedicalAppointment mMedicalAppointment) {
		return mAppointmentReservedRepository.findByMedicalAppointment(mMedicalAppointment);
	}

	@Override
	public MedicalAppointmentReserved findById(Long id) {
		return mAppointmentReservedRepository.findById(id).get();
	}

	@Override
	public MedicalAppointmentReserved update(Long id, MedicalAppointmentReservedDto mAppointmentReserved)
			throws NotFoundException {
		MedicalAppointmentReserved editMedicalAppointmentReserved = this.findById(id);
		if (editMedicalAppointmentReserved == null) {
			throw new NotFoundException("Reserva no encontrada");
		}
		mAppointmentReserved.setId(editMedicalAppointmentReserved.getId());
		mAppointmentReserved.setCreatedAt(editMedicalAppointmentReserved.getCreatedAt());
		mAppointmentReserved.setUpdatedAt(new Date());
		return mAppointmentReservedRepository.save(new MedicalAppointmentReserved(mAppointmentReserved));
	}

	@Override
	public MedicalAppointmentReserved create(MedicalAppointmentReservedDto mAppointmentReserved)
			throws BadRequestException, NumberFormatException, NotFoundException {
		MedicalAppointmentReserved newMedicalAppointmentReserved = new MedicalAppointmentReserved(mAppointmentReserved);

		// Revisar duplicidad
		MedicalAppointmentView mAppView = this.mAppointmenService
				.findInViewById(newMedicalAppointmentReserved.getMedicalAppointment().getId());
		if (mAppView == null) {
			throw new BadRequestException("Hora médica no existe");
		}

		if (mAppointmentReserved.getUser() == null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = this.userService.findById(Long.parseLong(auth.getPrincipal().toString()));
			mAppointmentReserved.setUser(user.toDto());
		}
		MedicalAppointment ma = new MedicalAppointment();
		ma.setId(mAppView.getId());

		MedicalAppointmentReserved mar = this.mAppointmentReservedRepository.findByMedicalAppointment(ma);
		if (mar != null) {
			if (mar.getStatus() != Status.CANCELED && mar.getStatus() != Status.DELETED) {
				throw new BadRequestException("Hora médica no está disponible");
			}
		}

		MedicalAppointmentReserved mApp = mAppointmentReservedRepository.save(newMedicalAppointmentReserved);
		this.sendEmailReserved(mApp, mAppView);
		return mApp;
	}

	@Override
	public void delete(Long id) throws NotFoundException {
		MedicalAppointmentReserved editMedicalAppointmentReserved = this.findById(id);
		if (editMedicalAppointmentReserved == null) {
			throw new NotFoundException("Reserva no encontrada");
		}
		editMedicalAppointmentReserved.setStatus(Status.DELETED);
		mAppointmentReservedRepository.save(editMedicalAppointmentReserved);
	}

	private boolean sendEmailReserved(MedicalAppointmentReserved mAppReserved, MedicalAppointmentView mAppView) {
		try {
			Configuration config = this.configurationService.find();
			User user = userService.findById(mAppReserved.getUser().getId());

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			String strDate = dateFormat.format(mAppView.getSchedule());

			String mensaje = "<p>¡Hola " + user.getName() + " " + user.getLastName() + "!,</p>"
					+ "<p>Se ha realizado la reserva médica con éxito.</p>" + "<p>Detallamos la reserva realizada:</p>"
					+ "<br/><table><tr><td><b>Horario</b></td><td>" + strDate
					+ "</td></tr><tr><td><b>Doctor(a)</b></td><td>" + mAppView.getDoctorName() + " "
					+ mAppView.getDoctorLastName() + "</td></tr><tr><td><b>Especialidad</b></td><td>"
					+ mAppView.getSpecialityName() + "</td></tr><tr><td><b>Centro Médico</b></td><td>"
					+ mAppView.getCenterName() + "</td></tr><tr><td><b>Dirección</b></td><td>"
					+ mAppView.getCenterAddress() + "</td></tr><tr><td><b>Edificio</b></td><td>"
					+ mAppView.getBuildingName() + "</td></tr><tr><td><b>Piso</b></td><td>" + mAppView.getOfficeFloor()
					+ "</td></tr><tr><td><b>Oficina</b></td><td>" + mAppView.getCenterName() + "</td></tr></table><br/>"
					+ "<table><tr><td><table border='0' cellpadding='0' cellspacing='0' class='btn btn-primary'><tbody>"
					+ "<tr><td align='left'><table border='0' cellpadding='0' cellspacing='0'><tbody><tr>"
					+ "<td><a href='" + config.getUrl() + "/agenda/reservas/mis-reservas?confirmar="
					+ mAppReserved.getId() + "' target='_blank'>"
					+ "Confirmar asistencia</a></td></tr></tbody></table></td></tr></tbody></table></td><td>"
					+ "<table border='0' cellpadding='0' cellspacing='0' class='btn btn-danger'><tbody>"
					+ "<tr><td align='left'><table border='0' cellpadding='0' cellspacing='0'><tbody><tr>"
					+ "<td><a href='" + config.getUrl() + "/agenda/reservas/mis-reservas?cancelar="
					+ mAppReserved.getId() + "' target='_blank'>"
					+ "Cancelar reserva</a></td></tr></tbody></table></td></tr></tbody></table></td></tr></table><br/>"
					+ "<p>¡Te esperamos!.</p>";

			return this.emailService.sendEmail(user.getEmail(), "Reserva de hora médica", mensaje, config);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private boolean sendEmailChangeStatus(MedicalAppointmentReserved mAppReserved, MedicalAppointmentView mAppView,
			Status status) {
		try {
			User user = userService.findById(mAppReserved.getUser().getId());

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			String strDate = dateFormat.format(mAppView.getSchedule());

			String mensaje = "";
			String asunto = "";

			if (status.equals(Status.CANCELED)) {
				MedicalCenter mCenter = this.mCenterService.findById(mAppView.getCenterId());
				mensaje = "<p>¡Hola " + user.getName() + " " + user.getLastName() + "!,</p>"
						+ "<p>Su reserva médica ha sido cancelada.</p>"
						+ "<p>Si tiene dudas nos puede contactar al <a href='tel:" + mCenter.getPhone() + "'>"
						+ mCenter.getPhone() + "</a> o al correo " + mCenter.getEmail() + "</p>"
						+ "<p>Muchas gracias.</p>";

				asunto = "Cancelación hora médica";
			} else if (status.equals(Status.CONFIRMED)) {
				mensaje = "<p>¡Hola " + user.getName() + " " + user.getLastName() + "!,</p>"
						+ "<p>Se ha confirmado la asistencia a su atención médica con éxito.</p>"
						+ "<p>Detallamos la reserva realizada:</p>" + "<br/><table><tr><td><b>Horario</b></td><td>"
						+ strDate + "</td></tr><tr><td><b>Doctor(a)</b></td><td>" + mAppView.getDoctorName() + " "
						+ mAppView.getDoctorLastName() + "</td></tr><tr><td><b>Especialidad</b></td><td>"
						+ mAppView.getSpecialityName() + "</td></tr><tr><td><b>Centro Médico</b></td><td>"
						+ mAppView.getCenterName() + "</td></tr><tr><td><b>Dirección</b></td><td>"
						+ mAppView.getCenterAddress() + "</td></tr><tr><td><b>Edificio</b></td><td>"
						+ mAppView.getBuildingName() + "</td></tr><tr><td><b>Piso</b></td><td>"
						+ mAppView.getOfficeFloor() + "</td></tr><tr><td><b>Oficina</b></td><td>"
						+ mAppView.getCenterName() + "</td></tr></table><br/><p>¡Te esperamos!.</p>";
				asunto = "Confirmación de asistencia";
			} else {
				return false;
			}

			return this.emailService.sendEmail(user.getEmail(), asunto, mensaje);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<MedicalAppointmentView> findPatient() throws NotFoundException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Long userId = Long.parseLong(auth.getPrincipal().toString());
		User user = this.userService.findById(userId);
		if (user.getRole().equals(Role.DOCTOR)) {
			return this.mAppointmentViewRepository.findInViewByDoctorId(userId);
		} else {
			return this.mAppointmentViewRepository.findInViewByPatientId(userId);
		}
	}

	@Override
	public MedicalAppointmentReserved updateStatus(Long id, StatusDto status) throws NotFoundException {
		MedicalAppointmentReserved reserva = this.findById(id);
		MedicalAppointmentView mAppView = this.mAppointmenService.findInViewByReservedId(id);
		if (mAppView == null) {
			throw new NotFoundException("Hora médica no existe");
		}

		reserva.setStatus(status.getStatus());
		MedicalAppointmentReserved mApp = this.mAppointmentReservedRepository.save(reserva);
		this.sendEmailChangeStatus(mApp, mAppView, status.getStatus());
		return mApp;
	}
}