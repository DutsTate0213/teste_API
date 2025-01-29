import React from "react";
import {
  Box,
  Button,
  FormControl,
  FormLabel,
  Input,
  VStack,
  Select,
} from "@chakra-ui/react";
import useFormCursoLogic from "./FormCursoLogic";

const FormCurso = () => {
  const {
    nome,
    setNome,
    turno,
    setTurno,
    handleSubmit,
  } = useFormCursoLogic();

  return (
    <Box p={4}>
      <form onSubmit={handleSubmit}>
        <VStack spacing={4}>
          <FormControl isRequired>
            <FormLabel>Nome do Curso</FormLabel>
            <Input
              value={nome}
              onChange={(e) => setNome(e.target.value)}
              placeholder="Nome do curso"
            />
          </FormControl>

          <FormControl isRequired>
            <FormLabel>Turno</FormLabel>
            <Select
              value={turno}
              onChange={(e) => setTurno(e.target.value)}
              placeholder="Selecione o turno"
            >
              <option value="MANHA">Manhã</option>
              <option value="TARDE">Tarde</option>
              <option value="NOITE">Noite</option>
            </Select>
          </FormControl>

          <Button type="submit" colorScheme="purple" width="full">
            Salvar
          </Button>
        </VStack>
      </form>
    </Box>
  );
};

export default FormCurso; 