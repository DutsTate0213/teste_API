import { useEffect, useState } from "react";
import { useToast } from "@chakra-ui/react";
import { insertDisciplina, getDisciplina, updateDisciplina } from "../../../service/DisciplinaService";

const useFormDisciplinaLogic = ({ isOpen, onClose, disciplinaId, initialNome, onSuccess }) => {
  const toast = useToast();
  const [nome, setNome] = useState(initialNome || "");
  const [isLoading, setIsLoading] = useState(false);
      

  // Simplify the useEffect hooks into a single one
  useEffect(() => {
    const fetchDisciplina = async () => {
      if (disciplinaId && !initialNome) {
        try {
          const response = await getDisciplina(disciplinaId);
          setNome(response.nome || "");
        } catch (error) {
          toast({
            title: "Erro",
            description: "Erro ao carregar dados da disciplina",
            status: "error",
            duration: 3000,
            isClosable: true,
            position: "top-right",
          });
          setNome("");
        }
      } else if (initialNome) {
        setNome(initialNome);
      }
    };
    
    if (isOpen) {
      fetchDisciplina();
    } else {
      setNome("");
    }
  }, [disciplinaId, isOpen, initialNome]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!nome) {
      toast({
        title: "Erro",
        description: "Preencha todos os campos antes de enviar.",
        status: "error",
        duration: 4000,
        position: "top-right",
        isClosable: true,
      });
      return;
    }
    
    setIsLoading(true);
    try {
      const disciplinaData = {
        nome,
      };

      if (disciplinaId) {
        await updateDisciplina(disciplinaId, disciplinaData);
      } else {
        await insertDisciplina(disciplinaData);
      }

      toast({
        title: "Sucesso",
        description: disciplinaId 
          ? "Disciplina atualizada com sucesso"
          : "Disciplina cadastrada com sucesso",
        status: "success",
        duration: 3000,
        isClosable: true,
        position: "top-right",
      });
      
      if (onSuccess) onSuccess();
      onClose();
      setNome("");

    } catch (error) {
      toast({
        title: "Erro",
        description: error.message || "Erro ao processar disciplina",
        status: "error",
        duration: 3000,
        isClosable: true,
        position: "top-right",
      });
    } finally {
      setIsLoading(false);
    }
  };

  return {
    nome,
    setNome,
    isLoading,
    handleSubmit,
  };
};

export default useFormDisciplinaLogic; 