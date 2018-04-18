package com.management.managers;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.management.dto.SegmentDTO;
import com.management.entities.Segment;
import com.management.interfaces.SegmentManagerInterface;
import com.management.repositories.SegmentRepository;

public class SegmentManager implements SegmentManagerInterface {
	
	private SegmentRepository segmentRepository;
	
	
	@Autowired
	public SegmentManager(SegmentRepository segmentRepository) {
		this.segmentRepository = segmentRepository;
	}

	public boolean Create(SegmentDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Segment segment;

		try {
			segment = mapper.map(dto, Segment.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		segmentRepository.save(segment);

		return true;
	}
	public SegmentDTO Read(int id) {
		ModelMapper mapper = new ModelMapper();
		SegmentDTO dto;

		try {
			Segment segment = segmentRepository.findOne(id);
			dto = mapper.map(segment, SegmentDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}

		return dto;
	}

	public ArrayList<SegmentDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<Segment> listEntities = (ArrayList<Segment>) segmentRepository.findAll();
		ArrayList<SegmentDTO> listDTO = new ArrayList<SegmentDTO>();

		for (Segment tmp : listEntities) {
			try {
				SegmentDTO dto = mapper.map(tmp, SegmentDTO.class);
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	public boolean Update(SegmentDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Segment tmp;

		try {
			tmp = mapper.map(dto, Segment.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		segmentRepository.save(tmp);

		return true;
	}

	public boolean Delete(int id) {
		try {
			segmentRepository.delete(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}

		return true;
	}

}
