import { useEffect, useState } from "react";
import { useToast } from "@chakra-ui/react";
import { insertCurso, getCurso, updateCurso } from "../../../service/CursoService";

const useFormCursoLogic = ({ isOpen, onClose, cursoId, initialNome, onSuccess }) => {
  const toast = useToast();
  const [nome, setNome] = useState(initialNome || "");
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    const fetchCurso = async () => {
      if (cursoId && !initialNome) {
        try {
          const response = await getCurso(cursoId);
          setNome(response.nome || "");
        } catch (error) {
          toast({
            title: "Erro",
            description: "Erro ao carregar dados do curso",
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
      fetchCurso();
    } else {
      setNome("");
    }
  }, [cursoId, isOpen, initialNome]);

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
      const cursoData = {
        nome,
      };

      if (cursoId) {
        await updateCurso(cursoId, cursoData);
      } else {
        await insertCurso(cursoData);
      }

      toast({
        title: "Sucesso",
        description: cursoId 
          ? "Curso atualizado com sucesso"
          : "Curso cadastrado com sucesso",
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
        description: error.message || "Erro ao processar curso",
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

export default useFormCursoLogic; 