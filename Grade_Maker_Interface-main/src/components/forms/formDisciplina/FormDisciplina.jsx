import React from "react";
import {
  Box,
  Button,
  FormControl,
  FormLabel,
  Input,
  VStack,
  useToast,
} from "@chakra-ui/react";
import useFormDisciplinaLogic from "./FormDisciplinaLogic";

const FormDisciplina = () => {
  const {
    nome,
    setNome,
    cargaHoraria,
    setCargaHoraria,
    handleSubmit,
  } = useFormDisciplinaLogic();

  return (
    <Box p={4}>
      <form onSubmit={handleSubmit}>
        <VStack spacing={4}>
          <FormControl isRequired>
            <FormLabel>Nome da Disciplina</FormLabel>
            <Input
              value={nome}
              onChange={(e) => setNome(e.target.value)}
              placeholder="Nome da disciplina"
            />
          </FormControl>

          <Button type="submit" colorScheme="purple" width="full">
            Salvar
          </Button>
        </VStack>
      </form>
    </Box>
  );
};

export default FormDisciplina; 