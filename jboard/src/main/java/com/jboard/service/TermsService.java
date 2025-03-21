package com.jboard.service;

import java.util.List;

import com.jboard.dao.TermsDAO;
import com.jboard.dto.TermsDTO;

public class TermsService {

	private static TermsService instance = new TermsService();
	public static TermsService getInstance() {
		return instance;
	}
	
	private TermsService() {}
	
	private TermsDAO dao = TermsDAO.getInstance();
	
	
	public void insertTerms(TermsDTO dto) {
		dao.insertTerms(dto);
	}
	
	public TermsDAO selectTerms() {
		return dao.selectTerms();
	}
	
	public List<TermsDTO> selectTermses() {		
		return dao.selectTermses();
	}
	
	public void updateTerms(TermsDTO dto) {
		dao.updateTerms(dto);
	}
	
	public void deleteTerms() {
		dao.deleteTerms();
	}
}