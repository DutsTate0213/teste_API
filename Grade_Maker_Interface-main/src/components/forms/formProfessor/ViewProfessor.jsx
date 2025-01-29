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
  Table,
  Thead,
  Tbody,
  Tr,
  Th,
  Td,
  Box,
  Flex,
  Select,
} from "@chakra-ui/react";
import useViewProfessorLogic from "./FormProfessorLogic";


const ViewProfessor = ({ isOpen, onClose, professor }) => {
  const {
    disponibilidades,
    anoSelecionado,
    setAnoSelecionado,
    semestreSelecionado,
    setSemestreSelecionado,
    disciplinasUnicas,
    diasSemana,
    turnos,
  } = useViewProfessorLogic(professor);

  return (
    <Modal isOpen={isOpen} onClose={onClose} size="xl">
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>Disponibilidade do Professor: {professor?.nome}</ModalHeader>
        <ModalCloseButton />
        <ModalBody>
          <Flex gap={4} mb={4}>
            <Select
              value={anoSelecionado}
              onChange={(e) => setAnoSelecionado(e.target.value)}
            >
              <option value={new Date().getFullYear()}>
                {new Date().getFullYear()}
              </option>
              <option value={new Date().getFullYear() + 1}>
                {new Date().getFullYear() + 1}
              </option>
            </Select>
            <Select
              value={semestreSelecionado}
              onChange={(e) => setSemestreSelecionado(e.target.value)}
            >
              <option value={1}>1º Semestre</option>
              <option value={2}>2º Semestre</option>
            </Select>
          </Flex>

          {disponibilidades.length > 0 ? (
            <Flex gap={6} direction="column">
              <Box>
                <Text fontSize="lg" fontWeight="bold" mb={2}>
                  Disciplinas Cadastradas
                </Text>
                <Table variant="simple" size="sm">
                  <Thead>
                    <Tr>
                      <Th>Disciplina</Th>
                      <Th>Dia</Th>
                      <Th>Turno</Th>
                    </Tr>
                  </Thead>
                  <Tbody>
                    {disponibilidades.map((disp, index) => (
                      <Tr key={index}>
                        <Td>{disp.disciplina.nome}</Td>
                        <Td>{disp.diaSemana.nome}</Td>
                        <Td>{disp.turno.nome}</Td>
                      </Tr>
                    ))}
                  </Tbody>
                </Table>
              </Box>
            </Flex>
          ) : (
            <Text>Nenhuma disponibilidade cadastrada para este período.</Text>
          )}
        </ModalBody>

        <ModalFooter>
          <Button colorScheme="blue" mr={3} onClick={onClose}>
            Fechar
          </Button>
        </ModalFooter>
      </ModalContent>
    </Modal>
  );
};

export default ViewProfessor;
