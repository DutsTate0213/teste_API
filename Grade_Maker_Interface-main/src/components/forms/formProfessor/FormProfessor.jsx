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
import useFormProfessorLogic from "./FormProfessorLogic";

const FormProfessor = ({ isOpen, onClose, professor }) => {
  const {
    disponibilidades,
    anoSelecionado,
    setAnoSelecionado,
    semestreSelecionado,
    setSemestreSelecionado
  } = useFormProfessorLogic(professor);

  // Obtém disciplinas únicas
  const disciplinasUnicas = [...new Set(disponibilidades.map(d => d.disciplina.nome))];

  // Agrupa disponibilidades por dia da semana
  const disponibilidadesPorDia = disponibilidades.reduce((acc, disp) => {
    const key = `${disp.diaSemana.descricao}-${disp.turno.descricao}`;
    if (!acc[key]) {
      acc[key] = [];
    }
    acc[key].push(disp.disciplina.nome);
    return acc;
  }, {});

  return (
    <Modal isOpen={isOpen} onClose={onClose} size="xl">
      <ModalOverlay />
      <ModalContent maxW="550px">
        <ModalHeader>{professor ? professor.nome : "Professor"}</ModalHeader>
        <ModalCloseButton />
        <ModalBody>
          <Flex gap={4} mb={4}>
            <Box>
              <Text mb={2}>Ano</Text>
              <Select
                value={anoSelecionado}
                onChange={(e) => setAnoSelecionado(e.target.value)}
              >
                <option value={2023}>2023</option>
                <option value={2024}>2024</option>
                <option value={2025}>2025</option>
              </Select>
            </Box>
            <Box>
              <Text mb={2}>Semestre</Text>
              <Select
                value={semestreSelecionado}
                onChange={(e) => setSemestreSelecionado(e.target.value)}
              >
                <option value={1}>Primeiro</option>
                <option value={2}>Segundo</option>
              </Select>
            </Box>
          </Flex>

          {disponibilidades.length > 0 ? (
            <Flex gap={6} direction="column">
              {/* Tabela de Disciplinas */}
              <Box>
                <Text fontSize="lg" fontWeight="bold" mb={2}>
                  Disciplinas Cadastradas
                </Text>
                <Table variant="simple" size="sm">
                  <Thead>
                    <Tr>
                      <Th>Disciplina</Th>
                    </Tr>
                  </Thead>
                  <Tbody>
                    {disciplinasUnicas.map((disciplina) => (
                      <Tr key={disciplina}>
                        <Td>{disciplina}</Td>
                      </Tr>
                    ))}
                  </Tbody>
                </Table>
              </Box>

              {/* Tabela de Disponibilidade */}
              <Box>
                <Text fontSize="lg" fontWeight="bold" mb={2}>
                  Disponibilidade
                </Text>
                <Table variant="simple" size="sm">
                  <Thead>
                    <Tr>
                      <Th>Dia - Turno</Th>
                    </Tr>
                  </Thead>
                  <Tbody>
                    {Object.entries(disponibilidadesPorDia).map(([horario]) => (
                      <Tr key={horario}>
                        <Text fontWeight="medium" ml={5} mb={1}>{horario}</Text>
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

export default FormProfessor;
  