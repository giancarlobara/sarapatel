namespace BoundedBuffer
{
    public class Consumidor
    {
        private List<int?> _buffer;
        private readonly Semaphore _semaphore;

        public Consumidor(List<int?> buffer, Semaphore semaphore)
        {
            _buffer = buffer;
            _semaphore = semaphore;
        }

        public async Task Consumir(string nome)
        {
            await Task.Delay(1);

            int? dado = 0;

            try
            {
                _semaphore.WaitOne();

                    // Não consumir quando está vazio
                    if (_buffer.Count == 0)
                    {
                        Console.WriteLine($"Consumidor encontrou buffer vazio {DateTime.Now.Ticks}");
                        return;
                    }
                
                    dado = _buffer[_buffer.Count - 1];

                    _buffer.RemoveAt(_buffer.Count - 1);
                    Console.WriteLine($"Consumidor {nome} consumiu dado {dado} {DateTime.Now.Ticks}");
            }

            catch( Exception ex )
            {
                Console.WriteLine($"Consumidor {nome} exceção ao tentar remover dado {dado}");
            }
            finally{
                _semaphore.Release();
            }
        }
    }
}
