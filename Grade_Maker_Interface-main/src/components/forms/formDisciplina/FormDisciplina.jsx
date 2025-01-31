import React from "react";
import {
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalBody,
  ModalCloseButton,
  Box,
  Button,
  FormControl,
  FormLabel,
  Input,
  Select,
} from "@chakra-ui/react";
import useFormDisciplinaLogic from "./FormDisciplinaLogic";

const FormDisciplina = ({ isOpen, onClose, disciplinaId, initialNome, onSuccess }) => {
  const {
    nome,
    setNome,
    handleSubmit,
    isLoading
  } = useFormDisciplinaLogic({ isOpen, onClose, disciplinaId, initialNome, onSuccess });

  return (
    <Modal isOpen={isOpen} onClose={onClose}>
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>
          {disciplinaId ? 'Editar Disciplina' : 'Adicionar Disciplina'}
        </ModalHeader>
        <ModalCloseButton />
        <ModalBody>
          <Box p={4}>
            <form onSubmit={handleSubmit}>
              <FormControl isRequired mb={4}>
                <FormLabel>Nome da Disciplina</FormLabel>
                <Input
                  value={nome}
                  onChange={(e) => setNome(e.target.value)}
                  placeholder="Nome da disciplina"
                />
              </FormControl>
              <Button 
                type="submit" 
                colorScheme="purple" 
                width="full"
                isLoading={isLoading}
              >
                {disciplinaId ? 'Atualizar' : 'Salvar'}
              </Button>
            </form>
          </Box>
        </ModalBody>
      </ModalContent>
    </Modal>
  );
};

export default FormDisciplina; 