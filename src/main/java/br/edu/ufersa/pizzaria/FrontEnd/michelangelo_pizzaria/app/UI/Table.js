import TableHeader from "./TableHeader";
import TableBody from "./TableBody";

const Table = ({dados}) => {
  return (
    <table>
      <TableHeader />
      <TableBody agendaSemanal={dados}/>
    </table>
  );
};

export default Table;