import { useState } from "react";
import { insertProfessor } from "../../../service/ProfessorService";
import { useToast } from "@chakra-ui/react";

const useFormProfessorLogic = (onClose) => {
    const [professor, setProfessor] = useState({});
    const toast = useToast();

    const handleSubmitProfessor = async () => {
        try {
            if (!professor.nome || professor.nome.trim() === '') {
                toast({
                    title: "Erro",
                    description: "O nome do professor é obrigatório",
                    status: "error",
                    duration: 3000,
                    isClosable: true,
                });
                return;
            }

            await insertProfessor(professor);
            toast({
                title: "Sucesso",
                description: "Professor cadastrado com sucesso",
                status: "success",
                duration: 3000,
                isClosable: true,
            });
            handleCancelar();
            onClose();
        } catch (error) {
            toast({
                title: "Erro",
                description: "Erro ao cadastrar professor",
                status: "error",
                duration: 3000,
                isClosable: true,
            });
            console.error("Erro ao salvar professor:", error);
        }
    }

    const handleCancelar = () => {
        setProfessor({});
    }

    return {
        professor,
        setProfessor,
        handleSubmitProfessor,
        handleCancelar
    };
}

export default useFormProfessorLogic;

