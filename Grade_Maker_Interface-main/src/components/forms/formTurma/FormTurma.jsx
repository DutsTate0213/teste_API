import React from "react";
import {
  Box,
  Button,
  FormControl,
  FormLabel,
  Input,
  VStack,
  Select,
  NumberInput,
  NumberInputField,
} from "@chakra-ui/react";
import useFormTurmaLogic from "./FormTurmaLogic";

const FormTurma = () => {
  const {
    nome,
    setNome,
    curso,
    setCurso,
    periodo,
    setPeriodo,
    cursos,
    handleSubmit,
  } = useFormTurmaLogic();

  return (
    <Box p={4}>
      <form onSubmit={handleSubmit}>
        <VStack spacing={4}>
          <FormControl isRequired>
            <FormLabel>Nome da Turma</FormLabel>
            <Input
              value={nome}
              onChange={(e) => setNome(e.target.value)}
              placeholder="Nome da turma"
            />
          </FormControl>

          <FormControl isRequired>
            <FormLabel>Curso</FormLabel>
            <Select
              value={curso}
              onChange={(e) => setCurso(e.target.value)}
              placeholder="Selecione o curso"
            >
              {cursos.map((curso) => (
                <option key={curso.id} value={curso.id}>
                  {curso.nome}
                </option>
              ))}
            </Select>
          </FormControl>

          <FormControl isRequired>
            <FormLabel>Período</FormLabel>
            <NumberInput min={1} max={10} value={periodo} onChange={(value) => setPeriodo(value)}>
              <NumberInputField placeholder="Período" />
            </NumberInput>
          </FormControl>

          <Button type="submit" colorScheme="purple" width="full">
            Salvar
          </Button>
        </VStack>
      </form>
    </Box>
  );
};

export default FormTurma; 