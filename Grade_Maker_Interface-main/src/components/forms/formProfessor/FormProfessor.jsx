import useFormProfessorLogic from "./FormProfessorLogic";
import {
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalBody,
  ModalFooter,
  ModalCloseButton,
  Button,
  Input,
  Text,
  FormControl,
  FormLabel,
} from "@chakra-ui/react";

const FormProfessor = ({ isOpen, onClose }) => {
  const { professor, setProfessor, handleSubmitProfessor, handleCancelar } = useFormProfessorLogic(onClose);

  return (
    <Modal isOpen={isOpen} onClose={onClose} size="xl">
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>
          <Text>Cadastro de Professor</Text>
        </ModalHeader>
        <ModalCloseButton />
        <ModalBody>
          <FormControl>
            <FormLabel>Nome do Professor</FormLabel>
            <Input
              type="text"
              name="nome"
              value={professor.nome || ''}
              onChange={(e) => setProfessor({ ...professor, nome: e.target.value })}
              placeholder="Digite o nome do professor"
            />
          </FormControl>
        </ModalBody>
        <ModalFooter>
          <Button colorScheme="purple" mr={3} onClick={handleSubmitProfessor}> 
            Salvar
          </Button>
          <Button variant="ghost" onClick={() => {
            handleCancelar();
            onClose();
          }}>
            Cancelar
          </Button>
        </ModalFooter>
      </ModalContent>
    </Modal>
  );
};

export default FormProfessor;