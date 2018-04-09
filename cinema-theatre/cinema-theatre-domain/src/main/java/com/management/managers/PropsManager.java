package com.management.managers;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.dto.PropsDTO;
import com.management.entities.Props;
import com.management.interfaces.PropsManagerInterface;
import com.management.repositories.PropsRepository;

/**
 * 
 * @author Nikola Stojanovic
 *
 */
@Service
@Transactional
public class PropsManager implements PropsManagerInterface {

	private PropsRepository propsRepository;

	@Autowired
	public PropsManager(PropsRepository propsRepository) {
		this.propsRepository = propsRepository;
	}

	public boolean Create(PropsDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Props props;

		try {
			props = mapper.map(dto, Props.class);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		propsRepository.save(props);

		return true;
	}

	public PropsDTO Read(int id) {
		ModelMapper mapper = new ModelMapper();
		PropsDTO dto;

		try {
			Props props = propsRepository.findOne(id);
			dto = mapper.map(props, PropsDTO.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}

		return dto;
	}

	public ArrayList<PropsDTO> ReadAll() {
		ModelMapper mapper = new ModelMapper();
		ArrayList<Props> listEntities = (ArrayList<Props>) propsRepository
				.findAll();
		ArrayList<PropsDTO> listDTO = new ArrayList<PropsDTO>();

		for (Props tmp : listEntities) {
			try {
				PropsDTO dto = mapper.map(tmp, PropsDTO.class);
				listDTO.add(dto);
			} catch (Exception exc) {
				exc.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	public boolean Update(PropsDTO dto) {
		ModelMapper mapper = new ModelMapper();
		Props tmp;

		try {
			tmp = mapper.map(dto, Props.class);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		propsRepository.save(tmp);

		return true;
	}

	public boolean Delete(int id) {
		try {
			propsRepository.delete(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}

		return true;
	}

}
