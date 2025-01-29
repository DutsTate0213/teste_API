import React, { useEffect, useState } from "react";
import {
  Box,
  Heading,
  VStack,
  HStack,
  Switch,
  Text,
  Divider,
  useToast,
} from "@chakra-ui/react";
import {
  getDiaSemana,
  getTurnos,
  updateDiaSemanaStatus,
  updateTurnoStatus,
} from "../service/ConfiguracaoService";

const Configuracao = () => {
  const [diasSemana, setDiasSemana] = useState([]);
  const [turnos, setTurnos] = useState([]);
  const toast = useToast();

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      const [diasResponse, turnosResponse] = await Promise.all([
        getDiaSemana(),
        getTurnos(),
      ]);
      setDiasSemana(diasResponse.data || []);
      setTurnos(turnosResponse.data || []);
    } catch (error) {
      showToast("Erro ao carregar dados", "error");
    }
  };

  const handleDiaSemanaToggle = async (id, currentStatus) => {
    try {
      const newStatus = currentStatus === 1 ? 0 : 1;
      await updateDiaSemanaStatus(id, newStatus);
      setDiasSemana(diasSemana.map(dia => 
        dia.id === id ? { ...dia, ativo: newStatus } : dia
      ));
      showToast("Status atualizado com sucesso", "success");
    } catch (error) {
      showToast("Erro ao atualizar status", "error");
    }
  };

  const handleTurnoToggle = async (id, currentStatus) => {
    try {
      const newStatus = currentStatus === 1 ? 0 : 1;
      await updateTurnoStatus(id, newStatus);
      setTurnos(turnos.map(turno => 
        turno.id === id ? { ...turno, ativo: newStatus } : turno
      ));
      showToast("Status atualizado com sucesso", "success");
    } catch (error) {
      showToast("Erro ao atualizar status", "error");
    }
  };

  const showToast = (message, status) => {
    toast({
      title: message,
      status: status,
      duration: 3000,
      isClosable: true,
    });
  };

  return (
    <Box p={6}>
      <Heading mb={6}>Configurações</Heading>
      
      <VStack spacing={6} align="stretch">
        <Box>
          <Heading size="md" mb={4}>Dias da Semana</Heading>
          <VStack spacing={3} align="stretch">
            {diasSemana.map((dia) => (
              <HStack key={dia.id} justify="space-between">
                <Text>{dia.descricao}</Text>
                <Switch
                  isChecked={dia.ativo === 1}
                  onChange={() => handleDiaSemanaToggle(dia.id, dia.ativo)}
                  colorScheme="purple"
                />
              </HStack>
            ))}
          </VStack>
        </Box>

        <Divider />

        <Box>
          <Heading size="md" mb={4}>Turnos</Heading>
          <VStack spacing={3} align="stretch">
            {turnos.map((turno) => (
              <HStack key={turno.id} justify="space-between">
                <Text>{turno.descricao}</Text>
                <Switch
                  isChecked={turno.ativo === 1}
                  onChange={() => handleTurnoToggle(turno.id, turno.ativo)}
                  colorScheme="purple"
                />
              </HStack>
            ))}
          </VStack>
        </Box>
      </VStack>
    </Box>
  );
};

export default Configuracao;
