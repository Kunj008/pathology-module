package com.pathology.service;

import com.pathology.dto.TestMasterRequestDTO;
import com.pathology.dto.TestMasterResponseDTO;
import com.pathology.entity.TestMaster;
import com.pathology.repository.TestMasterRepository;
import com.pathology.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestMasterService {

    private final TestMasterRepository testMasterRepository;

    @Transactional
    public TestMasterResponseDTO createTest(TestMasterRequestDTO dto) {
        if (testMasterRepository.existsByCode(dto.getCode())) {
            throw new IllegalArgumentException("Test with code " + dto.getCode() + " already exists");
        }
        if (testMasterRepository.existsByName(dto.getName())) {
            throw new IllegalArgumentException("Test with name " + dto.getName() + " already exists");
        }

        TestMaster test = TestMaster.builder()
                .name(dto.getName())
                .code(dto.getCode())
                .sampleType(dto.getSampleType())
                .normalRange(dto.getNormalRange())
                .price(dto.getPrice())
                .build();

        TestMaster savedTest = testMasterRepository.save(test);
        return mapToResponseDTO(savedTest);
    }

    public List<TestMasterResponseDTO> getAllTests() {
        return testMasterRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<TestMasterResponseDTO> searchTestsByName(String name) {
        return testMasterRepository.findByNameContainingIgnoreCase(name).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public TestMasterResponseDTO getTestById(Long id) {
        TestMaster test = testMasterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Test not found with id: " + id));
        return mapToResponseDTO(test);
    }

    private TestMasterResponseDTO mapToResponseDTO(TestMaster test) {
        return TestMasterResponseDTO.builder()
                .id(test.getId())
                .name(test.getName())
                .code(test.getCode())
                .sampleType(test.getSampleType())
                .normalRange(test.getNormalRange())
                .price(test.getPrice())
                .build();
    }
}
