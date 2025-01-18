const TableBody = ({agendaSemanal}) => {
  const rows = agendaSemanal.map(
    (row, index) => {
      return (
        <tr key={index}>
          <td>{row.dia}</td>
          <td>{row.disciplina}</td>
          <td>{row.horario}</td>
        </tr>
      )
    }
  )
  return (
    <tbody>
      {rows}
    </tbody>
  );
};

export default TableBody;