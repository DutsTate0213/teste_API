import React, { useState } from "react";
import {
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalFooter,
  ModalBody,
  ModalCloseButton,
  Button,
  Text,
  Select,
  Table,
  Thead,
  Tbody,
  Tr,
  Th,
  Td,
  Box,
  Flex,
} from "@chakra-ui/react";
import useViewProfessorLogic from "./ViewProfessorLogic";

const ViewProfessor = ({ isOpen, onClose, professor }) => {
  const [isFormOpen, setIsFormOpen] = useState(false);
  
  const {
    disponibilidades,
    anoSelecionado,
    setAnoSelecionado,
    semestreSelecionado,
    setSemestreSelecionado,
    diasSemana,
    turnos,
    anosDisponiveis,
    cursos,
    professores,
  } = useViewProfessorLogic(professor);

  const handleFormClose = () => {
    setIsFormOpen(false);
    // Atualizar os dados após fechar o formulário
    // Isso será implementado no ViewProfessorLogic
  };

  return (
    <>
      <Modal isOpen={isOpen} onClose={onClose} size="xl">
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>{professor?.nome}</ModalHeader>
          <ModalCloseButton />
          <ModalBody>
            <Flex gap={4} mb={4}>
              <Select
                value={anoSelecionado}
                onChange={(e) => setAnoSelecionado(Number(e.target.value))}
              >
                {anosDisponiveis.map((ano) => (
                  <option key={ano} value={ano}>
                    {ano}
                  </option>
                ))}
              </Select>
              <Select
                value={semestreSelecionado}
                onChange={(e) => setSemestreSelecionado(Number(e.target.value))}
              >
                <option value={1}>1º Semestre</option>
                <option value={2}>2º Semestre</option>
              </Select>
            </Flex>

            {disponibilidades.length > 0 ? (
              <Box>
                <Text fontSize="lg" fontWeight="bold" mb={4}>
                  Disponibilidades
                </Text>
                <Table variant="simple">
                  <Thead>
                    <Tr>
                      <Th>Dia</Th>
                      <Th>Turno</Th>
                      <Th>Disciplina</Th>
                    </Tr>
                  </Thead>
                  <Tbody>
                    {disponibilidades.map((disp, index) => (
                      <Tr key={index}>
                        <Td>{disp.diaSemana.descricao}</Td>
                        <Td>{disp.turno.descricao}</Td>
                        <Td>{disp.disciplina.nome}</Td>
                      </Tr>
                    ))}
                  </Tbody>
                </Table>
              </Box>
            ) : (
              <Text>Nenhuma disponibilidade cadastrada para este período.</Text>
            )}
          </ModalBody>

          <ModalFooter>
            <Button colorScheme="purple" onClick={onClose} mr="auto">
              Fechar
            </Button>
            <Button colorScheme="purple" onClick={(onClose)} ml="auto">
              Editar Disponibilidade
            </Button>
          </ModalFooter>
        </ModalContent>
      </Modal>
    </>
  );
};

export default ViewProfessor;
