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

const FormCurso = ({ nome }) => {
  const {
    nome,
    setNome,
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

          <Button type="submit" colorScheme="purple" width="full">
            Salvar
          </Button>
          <Button type="submit" colorScheme="purple" width="full">
            Salvar
          </Button>
        </VStack>
      </form>
    </Box>
  );
};

export default FormCurso; 