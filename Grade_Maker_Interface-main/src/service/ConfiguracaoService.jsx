import Api from "./Api";

export const getDiaSemana = async () => {
  try {
    const response = await Api.get("/dia_semana");
    if (response.data) {
      return response.data;
    }
    throw new Error("Formato de dados inesperado");
  } catch (error) {
    console.error("Erro ao buscar dias da semana:", error);
    throw error;
  }
};

export const getTurnos = async () => {
  try {
    const response = await Api.get("/turno");
    if (response.data) {
      return response.data;
    }
    throw new Error("Formato de dados inesperado");
  } catch (error) {
    console.error("Erro ao buscar turnos:", error);
    throw error;
  }
};

export const updateDiaSemanaStatus = async (id, status) => {
  try {
    const response = await Api.patch(`/dia_semana/${id}/status`, { ativo: status });
    return response.data;
  } catch (error) {
    console.error("Erro ao atualizar status do dia da semana:", error);
    throw error;
  }
};

export const updateTurnoStatus = async (id, status) => {
  try {
    const response = await Api.patch(`/turno/${id}/status`, { ativo: status });
    return response.data;
  } catch (error) {
    console.error("Erro ao atualizar status do turno:", error);
    throw error;
  }
};
