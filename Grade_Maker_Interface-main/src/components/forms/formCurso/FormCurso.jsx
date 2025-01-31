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
} from "@chakra-ui/react";
import useFormCursoLogic from "./FormCursoLogic";

const FormCurso = ({ isOpen, onClose, cursoId, initialNome, onSuccess }) => {
  const {
    nome,
    setNome,
    handleSubmit,
    isLoading
  } = useFormCursoLogic({ isOpen, onClose, cursoId, initialNome, onSuccess });

  return (
    <Modal isOpen={isOpen} onClose={onClose}>
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>
          {cursoId ? 'Editar Curso' : 'Adicionar Curso'}
        </ModalHeader>
        <ModalCloseButton />
        <ModalBody>
          <Box p={4}>
            <form onSubmit={handleSubmit}>
              <FormControl isRequired mb={4}>
                <FormLabel>Nome do Curso</FormLabel>
                <Input
                  value={nome}
                  onChange={(e) => setNome(e.target.value)}
                  placeholder="Nome do curso"
                />
              </FormControl>
              <Button 
                type="submit" 
                colorScheme="purple" 
                width="full"
                isLoading={isLoading}
              >
                {cursoId ? 'Atualizar' : 'Salvar'}
              </Button>
            </form>
          </Box>
        </ModalBody>
      </ModalContent>
    </Modal>
  );
};

export default FormCurso; 