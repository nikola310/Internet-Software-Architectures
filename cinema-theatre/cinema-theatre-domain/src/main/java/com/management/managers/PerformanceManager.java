package com.management.managers;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.dto.PerformanceDTO;
import com.management.entities.Performance;
import com.management.interfaces.PerformanceManagerInterface;
import com.management.repositories.PerformanceRepository;

/**
 * @author Zivko Stanisic
 *
 */
@Service
@Transactional
public class PerformanceManager implements PerformanceManagerInterface {

	private PerformanceRepository performanceRepository;

	@Autowired
	public PerformanceManager(PerformanceRepository performanceRepository) {
		this.performanceRepository = performanceRepository;
	}

	public boolean Create(PerformanceDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Performance performance;

		try {
			performance = mapper.map(dto, Performance.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		performanceRepository.save(performance);

		return true;
	}

	public PerformanceDTO Read(int id) {
		ModelMapper mapper = new ModelMapper();
		PerformanceDTO dto;

		try {
			Performance performance = performanceRepository.findOne(id);
			dto = mapper.map(performance, PerformanceDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}

		return dto;
	}

	public ArrayList<PerformanceDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<Performance> listEntities = (ArrayList<Performance>) performanceRepository.findAll();
		ArrayList<PerformanceDTO> listDTO = new ArrayList<PerformanceDTO>();

		for (Performance tmp : listEntities) {
			try {
				PerformanceDTO dto = mapper.map(tmp, PerformanceDTO.class);
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	public boolean Update(PerformanceDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Performance tmp;

		try {
			tmp = mapper.map(dto, Performance.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		performanceRepository.save(tmp);

		return true;
	}

	public boolean Delete(int id) {
		try {
			performanceRepository.delete(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}

		return true;
	}
}
